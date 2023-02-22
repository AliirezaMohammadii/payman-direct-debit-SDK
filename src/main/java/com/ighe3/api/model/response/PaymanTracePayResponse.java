package com.ighe3.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanTracePayResponse {
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

    // TODO: to be enum
    @JsonProperty("transaction_type")
    private String transactionType;
    @JsonProperty("reference_id")
    private String referenceId;
    @JsonProperty("trace_id")
    private String traceId;
    @JsonProperty("transaction_amount")
    private String transactionAmount;
    @JsonProperty("transaction_time")
    private String transactionTime;
    @JsonProperty("batch_id")
    private String batchId;
    @JsonProperty("commission_amount")
    private String commissionAmount;

    // TODO: to be enum
    private String status;
    @JsonProperty("bill_id")
    private String billId;
    @JsonProperty("pay_id")
    private String payId;
}
