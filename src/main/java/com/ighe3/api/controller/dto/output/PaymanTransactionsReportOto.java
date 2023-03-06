package com.ighe3.api.controller.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PaymanTransactionsReportOto {
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
    // TODO: to be enum;
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
    private Date serverDate;
    @JsonProperty("client_date")
    private Date clientDate;
}
