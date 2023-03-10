package com.ighe3.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanChangeStatusRequest {

    @JsonProperty("new_status")
    private String newStatus;

    @JsonProperty("payman_id")
    private String paymanId;
}
