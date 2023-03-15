package com.ighe3.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.client.request.PayRequest;
import lombok.Data;

public class PaymanPayRequest {

    @JsonProperty("payman_id")
    private String paymanId;

    private Double amount;

    private String description;

    @JsonProperty("client_transaction_date")
    private String clientTransactionDate;

    @JsonProperty("trace_id")
    private String traceId;

    @JsonProperty("pay_id")
    private String payId;

    @JsonProperty("commission_amount")
    private String commissionAmount;

    public PaymanPayRequest(PayRequest request) {
        this.paymanId = request.getPaymanId();
        this.amount = request.getAmount();
        this.description = request.getDescription();
        this.clientTransactionDate = request.getClientTransactionDate();
        this.traceId = request.getTraceId();
        this.payId = request.getPayId();
        this.commissionAmount = request.getCommissionAmount();
    }
}
