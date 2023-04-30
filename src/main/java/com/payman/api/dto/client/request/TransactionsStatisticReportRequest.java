package com.payman.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionsStatisticReportRequest {

    @JsonProperty("start_date")
    private Long startDateEpochMillis;

    @JsonProperty("end_date")
    private Long endDateEpochMillis;
}
