package com.ighe3.api.model.requestBodies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.model.PaymanObject;
import lombok.Data;

@Data
public class PaymanCreateRequestBodyObject {

    @JsonProperty("payman")
    private PaymanObject paymanObject;

    @JsonProperty("redirect_url")
    private String redirectUrl;

    @JsonProperty("trace_id")
    private String traceId;
}
