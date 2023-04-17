package com.payman.api.dto.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.TransactionsReportStatisticsResponseResult;
import lombok.Data;

@Data
public class PaymanTransactionsReportStatisticsResponseResult {

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("total_amount")
    private String totalAmount;

    @JsonProperty("total_count")
    private String totalCount;

    public PaymanTransactionsReportStatisticsResponseResult(TransactionsReportStatisticsResponseResult transactionsReportStatisticsResponseResult) {
        this.bankCode = transactionsReportStatisticsResponseResult.getBankCode();
        this.transactionType = transactionsReportStatisticsResponseResult.getTransactionType();
        this.totalAmount = transactionsReportStatisticsResponseResult.getTotalAmount();
        this.totalCount = transactionsReportStatisticsResponseResult.getTotalCount();
    }
}
