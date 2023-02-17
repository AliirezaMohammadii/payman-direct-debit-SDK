package com.ighe3.api.model.requestBodies;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PaymanPayRequestBodyObject {

    @JsonProperty("payman_id")
    private String paymanId;

    private Double amount;

    private String description;

    @JsonProperty("client_transaction_date")
    private Date clientTransactionDate;

    @JsonProperty("trace_id")
    private String traceId;
}
