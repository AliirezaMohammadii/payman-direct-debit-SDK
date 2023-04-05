package com.payman.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TracePaymentRequest {

    @JsonProperty("trace_id")
    private String traceId;

    // TODO: check format (yyyy-mm-dd)
    private String date;
}
