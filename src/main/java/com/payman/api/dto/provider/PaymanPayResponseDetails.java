package com.payman.api.dto.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.PayResponseDetails;
import com.payman.api.utils.DateUtils;
import lombok.Data;

@Data
public class PaymanPayResponseDetails {

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

    public PaymanPayResponseDetails(PayResponseDetails payResponseDetails) {
        this.referenceId = payResponseDetails.getReferenceId();
        this.traceId = payResponseDetails.getTraceId();
        this.amount = payResponseDetails.getAmount();
        this.transactionTime = DateUtils.epochMillisToPaymanDateTimeFormat(payResponseDetails.getTransactionTimeEpochMillis());
        this.transactionDetailType = payResponseDetails.getTransactionDetailType();
        this.status = payResponseDetails.getStatus();
    }
}
