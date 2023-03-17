package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetPaymanRequest extends BaseRequest {

    @JsonProperty("payman_id")
    private String paymanId;
}
