package com.payman.api.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.PaymanGetAllPaymansRequestFilter;
import com.payman.api.dto.provider.PaymanTransactionsRequestFilter;
import com.payman.api.utils.DateUtils;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TransactionsRequestFilter {

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
    private Long fromTransactionDateEpochMillis;

    @JsonProperty("to_transaction_date")
    private Long toTransactionDateEpochMillis;

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

    public TransactionsRequestFilter(PaymanTransactionsRequestFilter paymanTransactionsRequestFilter) {
        this.paymanIds = paymanTransactionsRequestFilter.getPaymanIds();
        this.userIds = paymanTransactionsRequestFilter.getUserIds();
        this.traceId = paymanTransactionsRequestFilter.getTraceId();
        this.referenceId = paymanTransactionsRequestFilter.getReferenceId();
        this.transactionType = paymanTransactionsRequestFilter.getTransactionType();
        this.fromTransactionAmount = paymanTransactionsRequestFilter.getFromTransactionAmount();
        this.toTransactionAmount = paymanTransactionsRequestFilter.getToTransactionAmount();
        this.fromTransactionDateEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanTransactionsRequestFilter.getFromTransactionDate());
        this.toTransactionDateEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanTransactionsRequestFilter.getToTransactionDate());
        this.note = paymanTransactionsRequestFilter.getNote();
        this.sourceBankCode = paymanTransactionsRequestFilter.getSourceBankCode();
        this.destinationBankCode = paymanTransactionsRequestFilter.getDestinationBankCode();
        this.paymanStatuses = paymanTransactionsRequestFilter.getPaymanStatuses();
        this.transactionStatuses = paymanTransactionsRequestFilter.getTransactionStatuses();
    }
}
