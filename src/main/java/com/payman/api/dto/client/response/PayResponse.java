package com.payman.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.GetAllPaymansResponseResult;
import com.payman.api.dto.client.PayResponseDetails;
import com.payman.api.dto.provider.response.PaymanPayResponse;
import com.payman.api.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PayResponse {

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("trace_id")
    private String traceId;

    @JsonProperty("transaction_amount")
    private Double transactionAmount;

    @JsonProperty("transaction_time")
    private Long transactionTimeEpochMillis;

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
        this.transactionTimeEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanResponse.getTransactionTime());
        this.batchId = paymanResponse.getBatchId();
        this.commissionAmount = paymanResponse.getCommissionAmount();
        this.status = paymanResponse.getStatus();
        this.details = Optional.ofNullable(paymanResponse.getDetails()).orElse(Collections.emptyList())
                .stream().map(PayResponseDetails::new).collect(Collectors.toList());;
    }
}
