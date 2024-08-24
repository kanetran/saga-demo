package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String createPayment() {
        System.out.println("Create payment");
        return "Create payment";
    }

    public void Rollback_createPayment_NullPointException() {
        System.out.println("Rollback createPayment because NullPointException");
    }

    public void Rollback_createPayment_RollBackException() {
        System.out.println("Rollback createPayment because RollBackException");
    }
}
