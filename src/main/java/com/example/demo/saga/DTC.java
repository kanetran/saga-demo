package com.example.demo.saga;

import com.example.demo.saga.exception.CanNotRollbackException;
import com.example.demo.saga.exception.RollBackException;
import com.example.demo.saga.pojo.ActionBuilder;
import com.example.demo.saga.pojo.SagaItemBuilder;
import com.example.demo.saga.pojo.Scenarios;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

@Component
public class DTC {

    public boolean commit(Scenarios scenarios) throws Exception {
        validate(scenarios);
        for (int i = 0; i < scenarios.getScenario().size(); i++) {
            SagaItemBuilder scenario = scenarios.getScenario().get(i);
            ActionBuilder action = scenario.getAction();
            Object bean = action.getComponent();
            String method = action.getMethod();
            Object[] args = action.getArgs();

            try {
                invoke(bean, method, args);
            } catch (Exception e) {
                rollback(scenarios, i, e);
                return false;
            }
        }
        return true;
    }

    private void rollback(Scenarios scenarios, Integer failStep, Exception currentStepFailException) {
        for (int i = failStep; i >= 0; i--) {
            SagaItemBuilder scenario = scenarios.getScenario().get(i);
            Map<Class<? extends Exception>, ActionBuilder> behaviours = scenario.getBehaviour();
            Set<Class<? extends Exception>> exceptions = behaviours.keySet();
            ActionBuilder actionWhenException = null;

            if (failStep == i) {
                for(Class<? extends Exception> exception: exceptions) {
                    if (exception.isInstance(currentStepFailException)) {
                        actionWhenException = behaviours.get(exception);
                    }
                }
                if (actionWhenException == null) actionWhenException = behaviours.get(RollBackException.class);
            } else {
                actionWhenException = behaviours.get(RollBackException.class);
            }

            Object bean = actionWhenException.getComponent();
            String method = actionWhenException.getMethod();
            Object[] args = actionWhenException.getArgs();
            try {
                invoke(bean, method, args);
            } catch (Exception e) {
                throw new CanNotRollbackException("Error in %s belong to %s. Can not rollback transaction".formatted(method, bean.getClass()));
            }
        }
    }

    private void validate(Scenarios scenarios) throws Exception {
        for (int i = 0; i < scenarios.getScenario().size(); i++) {
            SagaItemBuilder scenario = scenarios.getScenario().get(i);
            ActionBuilder action = scenario.getAction();
            if (action.getComponent() == null) throw new Exception("Missing bean in scenario");
            if (action.getMethod() == null) throw new Exception("Missing method in scenario");

            Map<Class<? extends Exception>, ActionBuilder> behaviours = scenario.getBehaviour();
            Set<Class<? extends Exception>> exceptions = behaviours.keySet();
            if (exceptions.contains(null)) throw new Exception("Exception can not be null in scenario has method %s, bean %s " .formatted(action.getMethod(), action.getComponent().getClass()));
            if (!exceptions.contains(RollBackException.class)) throw new Exception("Missing default RollBackException in scenario has method %s, bean %s " .formatted(action.getMethod(), action.getComponent().getClass()));
        }
    }

    public String invoke(Object bean, String methodName, Object... args) throws Exception {
        try {
            Class<?>[] paramTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                paramTypes[i] = parameterType(args[i]);
            }
            Method method = bean.getClass().getDeclaredMethod(methodName, paramTypes);
            Object result = method.invoke(bean, args);
            return result != null ? result.toString() : null;
        } catch (Exception e) {
            throw e;
        }
    }

    private static Class<?> parameterType (Object o) {
        if (o instanceof Integer) {
           return int.class;
        } else if (o instanceof Boolean) {
            return boolean.class;
        } else if (o instanceof Double) {
            return double.class;
        } else if (o instanceof Float) {
            return float.class;
        } else if (o instanceof Long) {
            return long.class;
        } else if (o instanceof Short) {
            return short.class;
        } else if (o instanceof Byte) {
            return byte.class;
        } else if (o instanceof Character) {
            return char.class;
        } else {
            return o.getClass();
        }
    }
}
