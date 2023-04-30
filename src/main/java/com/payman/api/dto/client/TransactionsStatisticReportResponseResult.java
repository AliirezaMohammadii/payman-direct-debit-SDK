package com.payman.api.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.PaymanTransactionsStatisticReportResponseResult;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionsStatisticReportResponseResult {

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("total_amount")
    private String totalAmount;

    @JsonProperty("total_count")
    private String totalCount;

    @JsonProperty("count_of_over_draft_payment")
    private Long countOfOverDraftPayment;

    public TransactionsStatisticReportResponseResult(PaymanTransactionsStatisticReportResponseResult paymanTransactionsStatisticReportResponseResult) {
        this.bankCode = paymanTransactionsStatisticReportResponseResult.getBankCode();
        this.transactionType = paymanTransactionsStatisticReportResponseResult.getTransactionType();
        this.totalAmount = paymanTransactionsStatisticReportResponseResult.getTotalAmount();
        this.totalCount = paymanTransactionsStatisticReportResponseResult.getTotalCount();
        this.countOfOverDraftPayment = paymanTransactionsStatisticReportResponseResult.getCountOfOverDraftPayment();
    }
}
