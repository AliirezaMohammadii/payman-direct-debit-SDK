package com.payman.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.request.TransactionsStatisticReportRequest;
import com.payman.api.utils.DateUtils;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PaymanTransactionsStatisticReportRequest {

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    public PaymanTransactionsStatisticReportRequest(TransactionsStatisticReportRequest request) {
        this.startDate = DateUtils.epochMillisToPaymanDateTimeFormat(request.getStartDateEpochMillis());
        this.endDate = DateUtils.epochMillisToPaymanDateTimeFormat(request.getEndDateEpochMillis());
    }
}
