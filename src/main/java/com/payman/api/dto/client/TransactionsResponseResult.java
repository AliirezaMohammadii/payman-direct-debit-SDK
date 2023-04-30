package com.payman.api.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.PaymanTransactionsResponseResult;
import com.payman.api.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TransactionsResponseResult {

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
    private Long transactionTimeEpochMillis;

    @JsonProperty("payman_id")
    private String paymanId;

    @JsonProperty("transaction_type")
    private String transactionType;

    private String description;

    public TransactionsResponseResult(PaymanTransactionsResponseResult paymanTransactionsResponseResult) {
        this.commissionAmount = paymanTransactionsResponseResult.getCommissionAmount();
        this.currency = paymanTransactionsResponseResult.getCurrency();
        this.destinationBankCode = paymanTransactionsResponseResult.getDestinationBankCode();
        this.referenceId = paymanTransactionsResponseResult.getReferenceId();
        this.sourceBankCode = paymanTransactionsResponseResult.getSourceBankCode();
        this.status = paymanTransactionsResponseResult.getStatus();
        this.traceId = paymanTransactionsResponseResult.getTraceId();
        this.transactionAmount = paymanTransactionsResponseResult.getTransactionAmount();
        this.transactionTimeEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanTransactionsResponseResult.getTransactionTime());
        this.paymanId = paymanTransactionsResponseResult.getPaymanId();
        this.transactionType = paymanTransactionsResponseResult.getTransactionType();
        this.description = paymanTransactionsResponseResult.getDescription();
    }
}
