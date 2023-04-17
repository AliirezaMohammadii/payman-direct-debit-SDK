package com.payman.api.dto.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.TransactionsResponseResult;
import com.payman.api.utils.DateUtils;
import lombok.Data;

@Data
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

    public PaymanTransactionsResponseResult(TransactionsResponseResult transactionsResponseResult) {
        this.commissionAmount = transactionsResponseResult.getCommissionAmount();
        this.currency = transactionsResponseResult.getCurrency();
        this.destinationBankCode = transactionsResponseResult.getDestinationBankCode();
        this.referenceId = transactionsResponseResult.getReferenceId();
        this.sourceBankCode = transactionsResponseResult.getSourceBankCode();
        this.status = transactionsResponseResult.getStatus();
        this.traceId = transactionsResponseResult.getTraceId();
        this.transactionAmount = transactionsResponseResult.getTransactionAmount();
        this.transactionTime = DateUtils.epochMillisToPaymanDateTimeFormat(transactionsResponseResult.getTransactionTimeEpochMillis());
        this.paymanId = transactionsResponseResult.getPaymanId();
        this.transactionType = transactionsResponseResult.getTransactionType();
    }
}
