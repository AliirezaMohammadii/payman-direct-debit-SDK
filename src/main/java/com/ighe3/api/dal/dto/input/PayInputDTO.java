package com.ighe3.api.dal.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

// TODO: remove
@Data
public class PayInputDTO {
    private String paymanId;
    private Double amount;
    private String description;
    private Date clientTransactionDate;
    private String traceId;
}
