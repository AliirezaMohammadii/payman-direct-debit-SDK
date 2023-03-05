package com.ighe3.api.model.enums;

public enum Permission {
    PAY(1),
    BILL(2);

    public final int label;

    private Permission(int label) {
        this.label = label;
    }
}
