package com.example.demo.saga.pojo;

import java.util.ArrayList;
import java.util.List;

public class Scenarios {
    List<SagaItemBuilder> scenarios;

    public static Scenarios builder() {
        return new Scenarios();
    }

    public Scenarios scenario(SagaItemBuilder sagaItemBuilder) {
        if (scenarios == null) scenarios = new ArrayList<>();
        scenarios.add(sagaItemBuilder);
        return this;
    }

    public List<SagaItemBuilder> getScenario() {
        return scenarios;
    }
}
