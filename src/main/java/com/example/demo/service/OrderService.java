package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String prepareOrder(String name, int number) {
        System.out.println("Prepare order for %s with order id %d ".formatted(name, number));
        return "Prepare order for %s with order id %d ".formatted(name, number);
    }

    public void Rollback_prepareOrder_NullPointException() {
        System.out.println("Rollback prepareOrder because NullPointException");
    }

    public void Rollback_prepareOrder_RollBackException() {
        System.out.println("Rollback prepareOrder because RollBackException");
    }
}
