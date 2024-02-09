package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TraceCreationRequest extends BaseRequest {

    @JsonProperty("trace_id")
    private String traceId;
}
