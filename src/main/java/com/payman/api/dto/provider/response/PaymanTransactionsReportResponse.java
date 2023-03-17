package com.payman.api.dto.provider.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PaymanTransactionsReportResponse {

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
    private String serverDate;

    @JsonProperty("client_date")
    private String clientDate;
}
