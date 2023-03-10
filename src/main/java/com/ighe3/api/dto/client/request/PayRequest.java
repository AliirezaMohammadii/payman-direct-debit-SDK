package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PayRequest {

    @JsonProperty("payman_id")
    private String paymanId;

//    @JsonProperty("trace_id")
//    private String traceId;

    private Double amount;

    private String description;

    @JsonProperty("client_transaction_date")
    private String clientTransactionDate;

    @JsonProperty("pay_id")
    private String payId;

    @JsonProperty("commission_amount")
    private String commissionAmount;
}
