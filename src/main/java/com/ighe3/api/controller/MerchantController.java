package com.ighe3.api.controller;

import com.ighe3.api.service.payman.MerchantPermissionsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/merchants")
public class MerchantController {

    private final MerchantPermissionsService merchantPermissionsService;

    public MerchantController(MerchantPermissionsService merchantPermissionsService) {
        this.merchantPermissionsService = merchantPermissionsService;
    }

    @GetMapping("/permissions")
    @ResponseStatus(HttpStatus.OK)
    public Object getMerchantPermissions() throws RuntimeException {
        return merchantPermissionsService.getPermissionIds();
    }
}
