package com.payman.api.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.PaymanContract;
import com.payman.api.dto.provider.PaymanPayResponseDetails;
import com.payman.api.utils.DateUtils;
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
    private Long transactionTimeEpochMillis;

    @JsonProperty("transaction_detail_type")
    private String transactionDetailType;

    private String status;

    public PayResponseDetails(PaymanPayResponseDetails paymanPayResponseDetails) {
        this.referenceId = paymanPayResponseDetails.getReferenceId();
        this.traceId = paymanPayResponseDetails.getTraceId();
        this.amount = paymanPayResponseDetails.getAmount();
        this.transactionTimeEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanPayResponseDetails.getTransactionTime());
        this.transactionDetailType = paymanPayResponseDetails.getTransactionDetailType();
        this.status = paymanPayResponseDetails.getStatus();
    }
}
