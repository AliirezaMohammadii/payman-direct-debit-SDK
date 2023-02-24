package com.ighe3.api.model.request;

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
}
