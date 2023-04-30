package com.payman.api.dto.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.TransactionsRequestFilter;
import com.payman.api.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PaymanTransactionsRequestFilter {

    @JsonProperty("payman_ids")
    private List<String> paymanIds;

    @JsonProperty("user_ids")
    private List<String> userIds;

    @JsonProperty("trace_id")
    private String traceId;

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("from_transaction_amount")
    private Double fromTransactionAmount;

    @JsonProperty("to_transaction_amount")
    private Double toTransactionAmount;

    @JsonProperty("from_transaction_date")
    private String fromTransactionDate;

    @JsonProperty("to_transaction_date")
    private String toTransactionDate;

    @JsonProperty("note")
    private String note;

    @JsonProperty("source_bank_code")
    private String sourceBankCode;

    @JsonProperty("destination_bank_code")
    private String destinationBankCode;

    @JsonProperty("payman_statuses")
    private List<String> paymanStatuses;

    @JsonProperty("transaction_statuses")
    private List<String> transactionStatuses;

    public PaymanTransactionsRequestFilter(TransactionsRequestFilter transactionsRequestFilter) {
        if (transactionsRequestFilter == null)
            return;

        this.paymanIds = transactionsRequestFilter.getPaymanIds();
        this.userIds = transactionsRequestFilter.getUserIds();
        this.traceId = transactionsRequestFilter.getTraceId();
        this.referenceId = transactionsRequestFilter.getReferenceId();
        this.transactionType = transactionsRequestFilter.getTransactionType();
        this.fromTransactionAmount = transactionsRequestFilter.getFromTransactionAmount();
        this.toTransactionAmount = transactionsRequestFilter.getToTransactionAmount();
        this.fromTransactionDate = DateUtils.epochMillisToPaymanDateTimeFormat(transactionsRequestFilter.getFromTransactionDateEpochMillis());
        this.toTransactionDate = DateUtils.epochMillisToPaymanDateTimeFormat(transactionsRequestFilter.getToTransactionDateEpochMillis());
        this.note = transactionsRequestFilter.getNote();
        this.sourceBankCode = transactionsRequestFilter.getSourceBankCode();
        this.destinationBankCode = transactionsRequestFilter.getDestinationBankCode();
        this.paymanStatuses = transactionsRequestFilter.getPaymanStatuses();
        this.transactionStatuses = transactionsRequestFilter.getTransactionStatuses();
    }
}
