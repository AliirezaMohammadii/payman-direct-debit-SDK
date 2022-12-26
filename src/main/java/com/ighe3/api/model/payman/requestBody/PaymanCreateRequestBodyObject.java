package com.ighe3.api.model.payman.requestBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.model.payman.PaymanObject;
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
