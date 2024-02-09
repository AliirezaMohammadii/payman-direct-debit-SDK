package com.ighe3.api.dto.provider.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.PayResponseDetails;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PaymanPayResponse {

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("trace_id")
    private String traceId;

    @JsonProperty("transaction_amount")
    private Double transactionAmount;

    @JsonProperty("transaction_time")
    private String transactionTime;

    @JsonProperty("batch_id")
    private Long batchId;

    @JsonProperty("commission_amount")
    private Double commissionAmount;

    private String status;

    private List<PayResponseDetails> details;
}
