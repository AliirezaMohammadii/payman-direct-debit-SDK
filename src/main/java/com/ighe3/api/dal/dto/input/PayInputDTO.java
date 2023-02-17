package com.ighe3.api.dal.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

// TODO: remove
@Data
public class PayInputDTO {
    @JsonProperty("payman_id")
    private String paymanId;
    @JsonProperty("trace_id")
    private String traceId;
    private Double amount;
    private String description;
    @JsonProperty("client_transaction_date")
    private String clientTransactionDate;
}
