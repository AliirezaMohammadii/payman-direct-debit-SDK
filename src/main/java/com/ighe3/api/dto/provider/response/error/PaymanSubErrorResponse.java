package com.ighe3.api.dto.provider.response.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanSubErrorResponse {

    @JsonProperty("error")
    private String message;

    private String code;
}
