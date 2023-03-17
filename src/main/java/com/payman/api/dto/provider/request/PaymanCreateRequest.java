package com.payman.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.PaymanDetails;
import com.payman.api.dto.client.request.CreateRequest;

public class PaymanCreateRequest {

    private PaymanDetails payman;

    @JsonProperty("redirect_url")
    private String redirectUrl;

    @JsonProperty("trace_id")
    private String traceId;

    public PaymanCreateRequest(CreateRequest request) {
        this.payman = request.getPayman();
        this.redirectUrl = request.getRedirectUrl();
        this.traceId = request.getTraceId();
    }
}
