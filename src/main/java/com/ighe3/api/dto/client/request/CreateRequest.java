package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.PaymanDetails;
import lombok.Data;

@Data
public class CreateRequest extends BaseRequest {

    private PaymanDetails payman;

    @JsonProperty("redirect_url")
    private String redirectUrl;

    @JsonProperty("trace_id")
    private String traceId;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("national_code")
    private String nationalCode;
}
