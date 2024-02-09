package com.ighe3.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.provider.response.PaymanTransactionsReportResponse;
import lombok.Data;

import java.util.Date;

@Data
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
    private String serverDate;

    @JsonProperty("client_date")
    private String clientDate;

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
        this.serverDate = paymanResponse.getServerDate();
        this.clientDate = paymanResponse.getClientDate();
    }
}
