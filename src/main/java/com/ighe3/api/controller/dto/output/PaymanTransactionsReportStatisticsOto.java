package com.ighe3.api.controller.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.model.PaymanTransactionsReportStatisticsResult;
import lombok.Data;

import java.util.List;

@Data
public class PaymanTransactionsReportStatisticsOto {
    @JsonProperty("total_amount")
    private Double totalAmount;
    @JsonProperty("total_records")
    private Double totalRecords;
    private List<PaymanTransactionsReportStatisticsResult> results;
}
