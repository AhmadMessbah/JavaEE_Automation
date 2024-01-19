package com.mftplus.automation.model.enums;

public enum TransactionType {
    cash("نقد"),
    bankDeposit("واریز به بانک"),
    check("چک");

    private String title;

    TransactionType(String title) {
        this.title = title;
    }
}
