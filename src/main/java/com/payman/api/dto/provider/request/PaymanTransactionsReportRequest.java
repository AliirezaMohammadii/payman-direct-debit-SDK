package com.payman.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.request.TransactionsReportRequest;

public class PaymanTransactionsReportRequest {

    private Integer offset;

    private Integer length;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("bank_code")
    private String bankCode;

    public PaymanTransactionsReportRequest(TransactionsReportRequest request) {
        this.offset = request.getOffset();
        this.length = request.getLength();
        this.startDate = request.getStartDate();
        this.endDate = request.getEndDate();
        this.bankCode = request.getBankCode();
    }
}
