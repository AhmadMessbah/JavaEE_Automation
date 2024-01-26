package com.mftplus.automation.model.enums;

public enum PaymentType {
    cash("نقد"),
    bankDeposit("واریز به بانک"),
    check("چک");

    private String title;

    PaymentType(String title) {
        this.title = title;
    }
}
