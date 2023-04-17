package com.payman.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionsReportRequest {

    private Integer offset;

    private Integer length;

    @JsonProperty("start_date")
    private Long startDateEpochMillis;

    @JsonProperty("end_date")
    private Long endDateEpochMillis;

    @JsonProperty("bank_code")
    private String bankCode;
}
