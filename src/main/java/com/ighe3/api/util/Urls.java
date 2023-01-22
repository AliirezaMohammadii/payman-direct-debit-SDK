package com.ighe3.api.util;

public enum Urls {
    ACCESS_TOKEN("https://payman2.sandbox.faraboom.co/oauth/token"),
    CREATE("https://payman2.sandbox.faraboom.co/v1/payman/create"),
    PAYMAN_ID("https://payman2.sandbox.faraboom.co/v1/payman/getId"),
    TRACE_CREATE("https://payman2.sandbox.faraboom.co/v1/payman/create/trace"),
    PAY("https://payman2.sandbox.faraboom.co/v1/payman/pay"),
    TRACE_PAY("https://payman2.sandbox.faraboom.co/v1/payman/pay/trace"),
    UPDATE("https://payman2.sandbox.faraboom.co/v1/payman/update"),
    CHANGE_STATUS("https://payman2.sandbox.faraboom.co/v1/payman/status/change"),
    REPORT("https://payman2.sandbox.faraboom.co/v1/payman/report"),
    TRANSACTIONS("https://payman2.sandbox.faraboom.co/v1/payman/transactions"),
    PAYMAN_LIST("https://payman2.sandbox.faraboom.co/v1/payman/search"),
    MERCHANT_PERMISSIONS("https://payman2.sandbox.faraboom.co/v1/payman/creditor/permissions"),
    TRANSACTIONS_REPORT("https://payman2.sandbox.faraboom.co/v1/reports/transactions"),
    TRANSACTIONS_REPORT_STATISTICS("https://payman2.sandbox.faraboom.co/v1/reports/transactions/statistics");

    private final String value;

    public String getValue() { return this.value; }

    private Urls(String value) { this.value = value; }
}
