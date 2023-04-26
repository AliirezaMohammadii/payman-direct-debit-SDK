package com.payman.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.PaymanDetails;
import com.payman.api.dto.client.request.CreateRequest;
import com.payman.api.dto.provider.PaymanPaymanDetails;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PaymanCreateRequest {

    @JsonProperty("payman")
    private PaymanPaymanDetails payman;

    @JsonProperty("redirect_url")
    private String redirectUrl;

    @JsonProperty("trace_id")
    private String traceId;

    public PaymanCreateRequest(CreateRequest request) {
        this.payman = new PaymanPaymanDetails(request.getPayman());
        this.redirectUrl = request.getRedirectUrl();
        this.traceId = request.getTraceId();
    }
}
