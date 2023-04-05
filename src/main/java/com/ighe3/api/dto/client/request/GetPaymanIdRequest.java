package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetPaymanIdRequest {

    @JsonProperty("payman_code")
    private String paymanCode;
}
