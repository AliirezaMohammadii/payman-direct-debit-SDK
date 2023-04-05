package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChangeStatusRequest {

    @JsonProperty("payman_id")
    private String paymanId;

    @JsonProperty("new_status")
    private String newStatus;
}
