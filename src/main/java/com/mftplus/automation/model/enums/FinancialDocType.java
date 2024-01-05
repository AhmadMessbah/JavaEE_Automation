package com.mftplus.automation.model.enums;

public enum FinancialDocType {
    salaryPayment("پرداخت حقوق"),
    general("عمومی"),
    property("ارسالی از اموال"),
    stock("ارسالی از انبار");

    private String title;

    FinancialDocType(String title) {
        this.title = title;
    }
}
