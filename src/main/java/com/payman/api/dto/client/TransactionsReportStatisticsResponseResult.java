package com.payman.api.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.PaymanContract;
import com.payman.api.dto.provider.PaymanTransactionsReportStatisticsResponseResult;
import com.payman.api.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionsReportStatisticsResponseResult {

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("total_amount")
    private String totalAmount;

    @JsonProperty("total_count")
    private String totalCount;

    public TransactionsReportStatisticsResponseResult(PaymanTransactionsReportStatisticsResponseResult paymanTransactionsReportStatisticsResponseResult) {
        this.bankCode = paymanTransactionsReportStatisticsResponseResult.getBankCode();
        this.transactionType = paymanTransactionsReportStatisticsResponseResult.getTransactionType();
        this.totalAmount = paymanTransactionsReportStatisticsResponseResult.getTotalAmount();
        this.totalCount = paymanTransactionsReportStatisticsResponseResult.getTotalCount();
    }
}
