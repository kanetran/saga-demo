package com.example.demo.saga.pojo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SagaItemBuilder {
    private ActionBuilder action;
    private Map<Class<? extends Exception>, ActionBuilder> onBehaviour;

    public static SagaItemBuilder builder() {
        return new SagaItemBuilder();
    }

    public SagaItemBuilder action(ActionBuilder action) {
        this.action = action;
        return this;
    }

    public SagaItemBuilder onBehaviour(Class<? extends Exception> exception, ActionBuilder action) {
        if (Objects.isNull(onBehaviour)) onBehaviour = new HashMap<>();
        onBehaviour.put(exception, action);
        return this;
    }

    public ActionBuilder getAction() {
        return action;
    }

    public Map<Class<? extends Exception>, ActionBuilder> getBehaviour() {
        return onBehaviour;
    }
}