package com.ighe3.api.dto.enums;

public enum MerchantPermission {

    NORMAL_PAY(1),
    BILL_PAY(2);

    public final int code;

    private MerchantPermission(int code) {
        this.code = code;
    }
}
