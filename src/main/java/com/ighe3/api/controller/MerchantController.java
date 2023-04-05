package com.ighe3.api.controller;

import com.ighe3.api.dto.client.request.MerchantPermissionsRequest;
import com.ighe3.api.service.payman.MerchantPermissionsService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public Object getMerchantPermissions(HttpServletRequest httpServletRequest) throws IOException {
        return merchantPermissionsService.getPermissionsDetail(httpServletRequest);
    }
}
