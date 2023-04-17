package com.payman.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.request.TransactionsReportStatisticsRequest;
import com.payman.api.utils.DateUtils;

public class PaymanTransactionsReportStatisticsRequest {

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    public PaymanTransactionsReportStatisticsRequest(TransactionsReportStatisticsRequest request) {
        this.startDate = DateUtils.epochMillisToPaymanDateTimeFormat(request.getStartDateEpochMillis());
        this.endDate = DateUtils.epochMillisToPaymanDateTimeFormat(request.getEndDateEpochMillis());
    }
}
