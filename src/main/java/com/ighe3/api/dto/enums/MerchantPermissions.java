package com.ighe3.api.dto.enums;

public enum MerchantPermissions {
    NORMAL_PAY(1),
    BILL_PAY(2);

    public final int label;

    private MerchantPermissions(int label) {
        this.label = label;
    }
}
