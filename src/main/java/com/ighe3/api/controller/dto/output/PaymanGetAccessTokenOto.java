package com.ighe3.api.controller.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanGetAccessTokenOto {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("access_type")
    private String tokenType;
    private String scope;
    @JsonProperty("expires_in")
    private Long expiresIn;
}
