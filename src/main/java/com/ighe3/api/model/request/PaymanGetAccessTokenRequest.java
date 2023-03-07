package com.ighe3.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// no usage. because request is being sent in formBody format, not json.
@Data
public class PaymanGetAccessTokenRequest {
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("client_secret")
    private String clientSecret;
    @JsonProperty("grant_type")
    private String grantType;
}
