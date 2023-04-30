package com.payman.api.dto.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymanTransactionsStatisticReportResponseResult {

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
}
