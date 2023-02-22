package com.ighe3.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanGetAccessTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("access_type")
    private String tokenType;
    private String scope;
    @JsonProperty("expires_in")
    private Long expiresIn;
}
