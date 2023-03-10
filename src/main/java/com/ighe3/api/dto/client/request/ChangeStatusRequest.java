package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChangeStatusRequest {

    @JsonProperty("payman_id")
    String paymanId;

    @JsonProperty("new_status")
    String newStatus;
}
