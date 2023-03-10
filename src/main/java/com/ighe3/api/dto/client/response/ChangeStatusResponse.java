package com.ighe3.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChangeStatusResponse {

    @JsonProperty("payman_id")
    private String paymanId;

    private String status;
}
