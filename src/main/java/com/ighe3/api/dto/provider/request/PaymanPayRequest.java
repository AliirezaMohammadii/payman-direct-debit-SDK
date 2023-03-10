package com.ighe3.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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
}
