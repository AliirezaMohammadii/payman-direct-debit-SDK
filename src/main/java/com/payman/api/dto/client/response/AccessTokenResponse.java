package com.payman.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.response.PaymanAccessTokenResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccessTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    private String scope;

    @JsonProperty("expires_in")
    private Long expiresIn;

    public AccessTokenResponse(PaymanAccessTokenResponse paymanResponse) {
        this.accessToken = paymanResponse.getAccessToken();
        this.tokenType = paymanResponse.getTokenType();
        this.scope = paymanResponse.getScope();
        this.expiresIn = paymanResponse.getExpiresIn();
    }
}
