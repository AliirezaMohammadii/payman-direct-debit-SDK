package com.ighe3.api.dal.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// TODO: remove
@Data
public class PayInputDTO {
    private String paymanId;
    private Double amount;
    private String description;
    private String clientTransactionDate;
    private String traceId;
}
