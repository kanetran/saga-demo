package com.example.demo.saga.pojo;

public class ActionBuilder {
    private Object component;
    private String method;
    private Object[] args;

    public static ActionBuilder builder() {
        return new ActionBuilder();
    }

    public ActionBuilder component(Object component) {
        this.component = component;
        return this;
    }

    public ActionBuilder method(String method) {
        this.method = method;
        return this;
    }

    public ActionBuilder args(Object... args) {
        this.args = args;
        return this;
    }

    public Object getComponent() { return component; }
    public String getMethod() { return method; }
    public Object[] getArgs() { return args; }
}

