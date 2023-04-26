package com.payman.api.dto.provider.response.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanSubErrorResponse {

    @JsonProperty("error")
    private String message;

    private String code;

    private String reference;

    private String info;
}
