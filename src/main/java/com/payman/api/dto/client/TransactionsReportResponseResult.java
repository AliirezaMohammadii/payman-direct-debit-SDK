package com.payman.api.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.PaymanTransactionsReportResponseResult;
import com.payman.api.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionsReportResponseResult {

    @JsonProperty("commission_amount")
    private Double commissionAmount;

    private String currency;

    private String description;

    @JsonProperty("destination_bank")
    private String destinationBank;

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("source_bank")
    private String sourceBank;

    private String status;

    @JsonProperty("trace_id")
    private String traceId;

    @JsonProperty("transaction_amount")
    private Double transactionAmount;

    @JsonProperty("payman_id")
    private String paymanId;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("server_date")
    private Long serverDateEpochMillis;

    @JsonProperty("client_date")
    private Long clientDateEpochMillis;

    public TransactionsReportResponseResult(PaymanTransactionsReportResponseResult paymanResponseResult) {
        this.commissionAmount = paymanResponseResult.getCommissionAmount();
        this.currency = paymanResponseResult.getCurrency();
        this.description = paymanResponseResult.getDescription();
        this.destinationBank = paymanResponseResult.getDestinationBank();
        this.referenceId = paymanResponseResult.getReferenceId();
        this.sourceBank = paymanResponseResult.getSourceBank();
        this.status = paymanResponseResult.getStatus();
        this.traceId = paymanResponseResult.getTraceId();
        this.transactionAmount = paymanResponseResult.getTransactionAmount();
        this.paymanId = paymanResponseResult.getPaymanId();
        this.transactionType = paymanResponseResult.getTransactionType();
        this.clientDateEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanResponseResult.getServerDate());
        this.clientDateEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanResponseResult.getClientDate());
    }
}
