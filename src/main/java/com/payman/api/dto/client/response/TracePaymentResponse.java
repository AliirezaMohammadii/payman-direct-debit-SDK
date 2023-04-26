package com.payman.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.response.PaymanTracePayResponse;
import com.payman.api.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TracePaymentResponse {

    private String currency;

    private String description;

    @JsonProperty("destination_bank")
    private String destinationBank;

    @JsonProperty("destination_deposit")
    private String destinationDeposit;

    @JsonProperty("source_bank")
    private String sourceBank;

    @JsonProperty("source_deposit")
    private String sourceDeposit;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("trace_id")
    private String traceId;

    @JsonProperty("transaction_amount")
    private String transactionAmount;

    @JsonProperty("transaction_time")
    private Long transactionTimeEpochMillis;

    @JsonProperty("batch_id")
    private String batchId;

    @JsonProperty("commission_amount")
    private String commissionAmount;

    private String status;

    @JsonProperty("bill_id")
    private String billId;

    @JsonProperty("pay_id")
    private String payId;

    public TracePaymentResponse(PaymanTracePayResponse paymanResponse) {
        this.currency = paymanResponse.getCurrency();
        this.description = paymanResponse.getDescription();
        this.destinationBank = paymanResponse.getDestinationBank();
        this.destinationDeposit = paymanResponse.getDestinationDeposit();
        this.sourceBank = paymanResponse.getSourceBank();
        this.sourceDeposit = paymanResponse.getSourceDeposit();
        this.transactionType = paymanResponse.getTransactionType();
        this.referenceId = paymanResponse.getReferenceId();
        this.traceId = paymanResponse.getTraceId();
        this.transactionAmount = paymanResponse.getTransactionAmount();
        this.transactionTimeEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanResponse.getTransactionTime());
        this.batchId = paymanResponse.getBatchId();
        this.commissionAmount = paymanResponse.getCommissionAmount();
        this.status = paymanResponse.getStatus();
        this.billId = paymanResponse.getBillId();
        this.payId = paymanResponse.getPayId();
    }
}
