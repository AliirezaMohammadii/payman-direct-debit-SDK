package com.ighe3.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.TransactionsReportStatisticsResponseResult;
import lombok.Data;

import java.util.List;

@Data

public class TransactionsReportStatisticsResponse {

    @JsonProperty("total_amount")
    private Double totalAmount;

    @JsonProperty("total_records")
    private Double totalRecords;

    private List<TransactionsReportStatisticsResponseResult> results;
}
