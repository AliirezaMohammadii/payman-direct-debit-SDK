package com.ighe3.api.dto.enums;

public enum MerchantPermission {

    NORMAL_PAY(1),
    BILL_PAY(2);

    public final int label;

    private MerchantPermission(int label) {
        this.label = label;
    }
}
