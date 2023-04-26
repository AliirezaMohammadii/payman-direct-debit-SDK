package com.payman.api.controller;

import com.payman.api.dto.client.request.AccessTokenRequest;
import com.payman.api.dto.client.response.AccessTokenResponse;
import com.payman.api.service.payman.AccessTokenService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/v1/auths")
public class AuthController {

    private final AccessTokenService accessTokenService;

    public AuthController(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @PostMapping
    public AccessTokenResponse getAccessToken(HttpServletRequest httpServletRequest, @RequestBody AccessTokenRequest accessTokenRequest) throws IOException {
        return accessTokenService.getAccessToken(httpServletRequest, accessTokenRequest);
    }
}
