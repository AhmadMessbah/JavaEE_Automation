package com.mftplus.automation.model.enums;

public enum Role {
    admin("ادمین"),
    manager("مدیر"),
    employee("کارمند"),
    customer("ارباب رجوع");

    private final String title;

    Role(String title) {
        this.title = title;
    }

}
