package com.mftplus.automation.model.enums;

public enum Roles {
    admin("ادمین"),
    manager("مدیر"),
    employee("کارمند"),
    customer("ارباب رجوع");

    private String title;

    Roles(String title) {
        this.title = title;
    }

}
