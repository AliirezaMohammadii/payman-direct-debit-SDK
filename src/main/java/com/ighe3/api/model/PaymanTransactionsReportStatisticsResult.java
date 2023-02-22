package com.ighe3.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanTransactionsReportStatisticsResult {
    @JsonProperty("bank_code")
    private String bankCode;
    @JsonProperty("transaction_type")
    private String transactionType;
    @JsonProperty("total_amount")
    private String totalAmount;
    @JsonProperty("total_count")
    private String totalCount;
}
