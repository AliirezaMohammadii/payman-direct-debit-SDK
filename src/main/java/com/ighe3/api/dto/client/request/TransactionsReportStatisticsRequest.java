package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionsReportStatisticsRequest {

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;
}
