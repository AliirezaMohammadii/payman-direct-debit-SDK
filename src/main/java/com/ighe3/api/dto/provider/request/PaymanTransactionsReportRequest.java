package com.ighe3.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.client.request.TransactionsReportRequest;
import lombok.Data;

import java.util.Date;

public class PaymanTransactionsReportRequest {

    private Integer offset;

    private Integer length;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("end_date")
    private Date endDate;

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
