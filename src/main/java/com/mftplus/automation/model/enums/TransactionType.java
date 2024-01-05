package com.mftplus.automation.model.enums;

public enum TransactionType {
    buy("خرید"),
    sale("فروش"),
    receive("دریافت از"),
    paying("پرداخت به"),
    payingBill("پرداخت قبض"),
    returnPurchase("برگشت از خرید"),
    returnSales("برگشت از فروش");

    private String title;

    TransactionType(String title) {
        this.title = title;
    }
}
