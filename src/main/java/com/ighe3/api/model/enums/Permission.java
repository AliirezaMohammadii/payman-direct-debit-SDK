package com.ighe3.api.model.enums;

public enum Permission {
    NORMAL_PAY(1),
    BILL_PAY(2);

    public final int label;

    private Permission(int label) {
        this.label = label;
    }
}
