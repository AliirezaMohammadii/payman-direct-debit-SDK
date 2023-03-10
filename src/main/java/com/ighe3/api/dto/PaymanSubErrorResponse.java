package com.ighe3.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanSubErrorResponse {

    @JsonProperty("error")
    private String message;

    private String code;
}
