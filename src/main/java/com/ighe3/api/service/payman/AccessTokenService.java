package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.AccessTokenRequest;
import com.ighe3.api.dto.client.response.AccessTokenResponse;
import com.ighe3.api.dto.provider.response.PaymanAccessTokenResponse;
import com.ighe3.api.exception.BaseException;
import okhttp3.FormBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface AccessTokenService {

    String getAccessToken();

    AccessTokenResponse getAccessToken(HttpServletRequest httpServletRequest, String clientId, String clientSecret)
            throws IOException;
}
