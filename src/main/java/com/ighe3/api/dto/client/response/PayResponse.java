package com.ighe3.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.PayResponseDetails;
import com.ighe3.api.dto.provider.response.PaymanPayResponse;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PayResponse {

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

    public PayResponse(PaymanPayResponse paymanResponse) {
        this.referenceId = paymanResponse.getReferenceId();
        this.traceId = paymanResponse.getTraceId();
        this.transactionAmount = paymanResponse.getTransactionAmount();
        this.transactionTime = paymanResponse.getTransactionTime();
        this.batchId = paymanResponse.getBatchId();
        this.commissionAmount = paymanResponse.getCommissionAmount();
        this.status = paymanResponse.getStatus();
        this.details = paymanResponse.getDetails();
    }
}
