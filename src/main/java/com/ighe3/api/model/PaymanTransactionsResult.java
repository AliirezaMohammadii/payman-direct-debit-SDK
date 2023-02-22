package com.ighe3.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PaymanTransactionsResult {
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
    private Date transactionTime;
    @JsonProperty("payman_id")
    private String paymanId;
    @JsonProperty("transaction_type")
    private String transactionType;
}
