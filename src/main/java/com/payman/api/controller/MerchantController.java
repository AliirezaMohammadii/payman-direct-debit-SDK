package com.payman.api.controller;

import com.payman.api.dto.client.response.MerchantPermissionsResponse;
import com.payman.api.service.payman.MerchantPermissionsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/v1/merchants")
public class MerchantController {

    private final MerchantPermissionsService merchantPermissionsService;

    public MerchantController(MerchantPermissionsService merchantPermissionsService) {
        this.merchantPermissionsService = merchantPermissionsService;
    }

    @GetMapping("/permissions")
    public MerchantPermissionsResponse getMerchantPermissions(HttpServletRequest httpServletRequest) throws IOException {
        return merchantPermissionsService.getPermissionsDetail(httpServletRequest);
    }
}
