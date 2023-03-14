package com.ighe3.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Date fromTransactionDate;

    @JsonProperty("to_transaction_date")
    private Date toTransactionDate;

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
}
