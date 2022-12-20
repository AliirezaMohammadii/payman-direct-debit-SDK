package com.ighe3.api.util;

public enum Urls {
    GET_TOKEN("https://payman2.sandbox.faraboom.co/oauth/token"),
    CREATE_PAYMAN("https://payman2.sandbox.faraboom.co/v1/payman/create"),
    GET_PAYMAN_ID("https://payman2.sandbox.faraboom.co/v1/payman/getId"),
    TRACE_CREATE_PAYMAN("https://payman2.sandbox.faraboom.co/v1/payman/create/trace"),
    PAYMAN_PAY("https://payman2.sandbox.faraboom.co/v1/payman/pay"),
    TRACE_PAYMAN_PAY("https://payman2.sandbox.faraboom.co/v1/payman/pay/trace"),
    UPDATE_PAYMAN("https://payman2.sandbox.faraboom.co/v1/payman/update"),
    CHANGE_PAYMAN_STATUS("https://payman2.sandbox.faraboom.co/v1/payman/status/change"),
    PAYMAN_REPORT("https://payman2.sandbox.faraboom.co/v1/payman/report"),
    PAYMAN_TRANSACTION("https://payman2.sandbox.faraboom.co/v1/payman/transactions"),
    PAYMAN_SEARCH("https://payman2.sandbox.faraboom.co/v1/payman/search"),
    MERCHANT_PERMISSIONS("https://payman2.sandbox.faraboom.co/v1/payman/creditor/permissions"),
    REPORTS_TRANSACTIONS("https://payman2.sandbox.faraboom.co/v1/reports/transactions"),
    REPORTS_TRANSACTIONS_STATISTICS("https://payman2.sandbox.faraboom.co/v1/reports/transactions/statistics");

    private final String value;

    public String getValue() { return this.value; }

    private Urls(String value) { this.value = value; }
}
