package com.payman.api.dto.enums;

public enum MerchantPermission {

    DIRECT_DEBIT(1),
    BILL_PAY(2);

    public final int code;

    private MerchantPermission(int code) {
        this.code = code;
    }
}
