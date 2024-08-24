package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class BillingService {

    public String prepareBilling(String name, int number) {
        System.out.println("Prepare billing for %s with order id %d ".formatted(name, number));
        return "Prepare billing for %s with order id %d ".formatted(name, number);
    }

    public String createBilling(String name, int number) {
        throw  new NullPointerException();
    }

    public void Rollback_prepareBilling_NullPointException() {
        System.out.println("Rollback prepareBilling because NullPointException");
    }

    public void Rollback_prepareBilling_ArrayIndexOutOfBoundsException() {
        System.out.println("Rollback prepareBilling because ArrayIndexOutOfBoundsException");
    }

    public void Rollback_prepareBilling_RollBackException() {
        System.out.println("Rollback prepareBilling because RollBackException");
    }

    public void Rollback_createBilling_NullPointException() {
        System.out.println("Rollback createBilling because NullPointException");
    }

    public void Rollback_createBilling_ArrayIndexOutOfBoundsException() {
        System.out.println("Rollback createBilling because ArrayIndexOutOfBoundsException");
    }

    public void Rollback_createBilling_RollBackException() {
        System.out.println("Rollback createBilling because RollBackException");
    }
}
