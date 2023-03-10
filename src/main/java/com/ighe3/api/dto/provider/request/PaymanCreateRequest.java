package com.ighe3.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.PaymanDetails;
import lombok.Data;

@Data
public class PaymanCreateRequest {

    private PaymanDetails payman;

    @JsonProperty("redirect_url")
    private String redirectUrl;

    @JsonProperty("trace_id")
    private String traceId;
}
