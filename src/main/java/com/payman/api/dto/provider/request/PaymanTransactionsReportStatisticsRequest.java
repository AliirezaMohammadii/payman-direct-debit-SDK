package com.payman.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.request.TransactionsReportStatisticsRequest;

public class PaymanTransactionsReportStatisticsRequest {

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    public PaymanTransactionsReportStatisticsRequest(TransactionsReportStatisticsRequest request) {
        this.startDate = request.getStartDate();
        this.endDate = request.getEndDate();
    }
}
