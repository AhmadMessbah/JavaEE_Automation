package com.mftplus.automation.model.enums;

public enum PaymentType {
    cashPayment("نقد"),
    cardPayment("واریز به بانک"),
    checkPayment("چک");

    private String title;

    PaymentType(String title) {
        this.title = title;
    }
}
