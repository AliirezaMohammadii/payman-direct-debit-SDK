package com.payman.api.dto.provider.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.TransactionsReportStatisticsResponseResult;
import lombok.Data;

import java.util.List;

@Data
public class PaymanTransactionsReportStatisticsResponse {

    @JsonProperty("total_amount")
    private Double totalAmount;

    @JsonProperty("total_records")
    private Double totalRecords;

    private List<TransactionsReportStatisticsResponseResult> results;
}
