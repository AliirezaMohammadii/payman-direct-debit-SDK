package com.payman.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PayResponseDetails {

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("trace_id")
    private String traceId;

    private String amount;

    @JsonProperty("transaction_time")
    private String transactionTime;

    @JsonProperty("transaction_detail_type")
    private String transactionDetailType;

    private String status;
}
