package com.ighe3.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DetailsObject {
    @JsonProperty("reference_id")
    private String referenceId;
    @JsonProperty("trace_id")
    private String traceId;
    private String amount;
    @JsonProperty("transaction_time")
    private String transactionTime;
    @JsonProperty("transaction_detail_type")
    private String transactionDetailType;
    // TODO: to be enum
    private String status;
}
