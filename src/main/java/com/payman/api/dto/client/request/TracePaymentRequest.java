package com.payman.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TracePaymentRequest {

    @JsonProperty("trace_id")
    private String traceId;

    // remind that it's gonna be a query param, while sending to payman
    private Long dateEpochMillis;
}
