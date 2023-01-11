package com.ighe3.api.dal.dto.payman.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanPayInputDTO {

    @JsonProperty("payman_id")
    private String paymanId;

    private Double amount;

    private String description;

    @JsonProperty("client_transaction_date")
    private String clientTransactionDate;

    @JsonProperty("trace_id")
    private String traceId;
}
