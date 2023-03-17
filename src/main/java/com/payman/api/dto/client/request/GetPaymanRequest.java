package com.payman.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetPaymanRequest {

    @JsonProperty("payman_id")
    private String paymanId;
}
