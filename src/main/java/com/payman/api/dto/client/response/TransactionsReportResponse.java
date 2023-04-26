package com.payman.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.response.PaymanTransactionsReportResponse;
import com.payman.api.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionsReportResponse {

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

    public TransactionsReportResponse(PaymanTransactionsReportResponse paymanResponse) {
        this.commissionAmount = paymanResponse.getCommissionAmount();
        this.currency = paymanResponse.getCurrency();
        this.description = paymanResponse.getDescription();
        this.destinationBank = paymanResponse.getDestinationBank();
        this.referenceId = paymanResponse.getReferenceId();
        this.sourceBank = paymanResponse.getSourceBank();
        this.status = paymanResponse.getStatus();
        this.traceId = paymanResponse.getTraceId();
        this.transactionAmount = paymanResponse.getTransactionAmount();
        this.paymanId = paymanResponse.getPaymanId();
        this.transactionType = paymanResponse.getTransactionType();
        this.clientDateEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanResponse.getServerDate());
        this.clientDateEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanResponse.getClientDate());
    }
}
