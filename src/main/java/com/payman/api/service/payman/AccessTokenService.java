package com.payman.api.service.payman;

import com.payman.api.dto.client.request.AccessTokenRequest;
import com.payman.api.dto.client.response.AccessTokenResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface AccessTokenService {

    String getAccessToken();

    AccessTokenResponse getAccessToken(HttpServletRequest httpServletRequest, AccessTokenRequest accessTokenRequest)
            throws IOException;
}
