package com.payman.api.controller;

import com.payman.api.dto.client.response.AccessTokenResponse;
import com.payman.api.service.payman.AccessTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AccessTokenService accessTokenService;

    public AuthController(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @GetMapping
    public AccessTokenResponse getAccessToken(HttpServletRequest httpServletRequest,
                                              @RequestParam(value = "client_id") String clientId,
                                              @RequestParam(value = "client_secret") String clientSecret) throws IOException {
        return accessTokenService.getAccessToken(httpServletRequest, clientId, clientSecret);
    }
}
