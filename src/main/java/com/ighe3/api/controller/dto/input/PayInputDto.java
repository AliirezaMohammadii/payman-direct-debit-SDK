package com.ighe3.api.controller.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// TODO: remove
@Data
public class PayInputDto {
    @JsonProperty("payman_id")
    private String paymanId;
    @JsonProperty("trace_id")
    private String traceId;
    private Double amount;
    private String description;
    @JsonProperty("client_transaction_date")
    private String clientTransactionDate;
}
