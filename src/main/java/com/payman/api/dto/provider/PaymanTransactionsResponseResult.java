package com.payman.api.dto.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.TransactionsResponseResult;
import com.payman.api.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymanTransactionsResponseResult {

    @JsonProperty("commission_amount")
    private Double commissionAmount;

    private String currency;

    @JsonProperty("destination_bank_code")
    private String destinationBankCode;

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("source_bank_code")
    private String sourceBankCode;

    private String status;

    @JsonProperty("trace_id")
    private String traceId;

    @JsonProperty("transaction_amount")
    private Double transactionAmount;

    @JsonProperty("transaction_time")
    private String transactionTime;

    @JsonProperty("payman_id")
    private String paymanId;

    @JsonProperty("transaction_type")
    private String transactionType;

    private String description;
}
