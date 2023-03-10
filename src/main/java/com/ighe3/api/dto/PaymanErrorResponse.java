package com.ighe3.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PaymanErrorResponse {

    @JsonProperty("error")
    private String message;

    private String code;

    private List<PaymanSubErrorResponse> errors;
}
