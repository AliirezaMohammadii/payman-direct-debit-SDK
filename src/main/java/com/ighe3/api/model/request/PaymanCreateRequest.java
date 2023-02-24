package com.ighe3.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.model.PaymanModel;
import lombok.Data;

@Data
public class PaymanCreateRequest {
    @JsonProperty("payman")
    private PaymanModel paymanObject;
    @JsonProperty("redirect_url")
    private String redirectUrl;
    @JsonProperty("trace_id")
    private String traceId;
}
