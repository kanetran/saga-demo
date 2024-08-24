package com.example.demo.service;

import com.example.demo.saga.DTC;
import com.example.demo.saga.exception.RollBackException;
import com.example.demo.saga.pojo.ActionBuilder;
import com.example.demo.saga.pojo.SagaItemBuilder;
import com.example.demo.saga.pojo.Scenarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DTC dtc;

    public String test() throws Exception {
        Scenarios scenarios = Scenarios.builder()
                .scenario(
                        SagaItemBuilder.builder()
                                .action(ActionBuilder.builder().component(orderService).method("prepareOrder").args("tuanh.net", 123))
                                .onBehaviour(NullPointerException.class, ActionBuilder.builder().component(orderService).method("Rollback_prepareOrder_NullPointException").args())
                                .onBehaviour(RollBackException.class, ActionBuilder.builder().component(orderService).method("Rollback_prepareOrder_RollBackException").args())
                ).scenario(
                        SagaItemBuilder.builder()
                                .action(ActionBuilder.builder().component(billingService).method("prepareBilling").args("tuanh.net", 123))
                                .onBehaviour(NullPointerException.class, ActionBuilder.builder().component(billingService).method("Rollback_prepareBilling_NullPointException").args())
                                .onBehaviour(RollBackException.class, ActionBuilder.builder().component(billingService).method("Rollback_prepareBilling_RollBackException").args())
                ).scenario(
                         SagaItemBuilder.builder()
                                .action(ActionBuilder.builder().component(billingService).method("createBilling").args("tuanh.net", 123))
                                .onBehaviour(NullPointerException.class, ActionBuilder.builder().component(billingService).method("Rollback_createBilling_ArrayIndexOutOfBoundsException").args())
                                .onBehaviour(RollBackException.class, ActionBuilder.builder().component(billingService).method("Rollback_createBilling_RollBackException").args())
                ).scenario(
                        SagaItemBuilder.builder()
                                .action(ActionBuilder.builder().component(paymentService).method("createPayment").args())
                                .onBehaviour(NullPointerException.class, ActionBuilder.builder().component(paymentService).method("Rollback_createPayment_NullPointException").args())
                                .onBehaviour(RollBackException.class, ActionBuilder.builder().component(paymentService).method("Rollback_createPayment_RollBackException").args())
                );
        dtc.commit(scenarios);
        return "ok";
    }
}
