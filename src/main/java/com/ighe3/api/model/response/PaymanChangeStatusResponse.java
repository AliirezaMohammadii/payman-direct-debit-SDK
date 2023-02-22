package com.ighe3.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanChangeStatusResponse {
    @JsonProperty("payman_id")
    private String paymanId;
    private String status;
}
