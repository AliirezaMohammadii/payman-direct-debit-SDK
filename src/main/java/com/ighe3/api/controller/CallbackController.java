package com.ighe3.api.controller;

import com.ighe3.api.dto.enums.RedirectUrlStatus;
import com.ighe3.api.service.payman.PaymanIdService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/callback")
public class CallbackController {

    private final PaymanIdService paymanIdService;

    public CallbackController(PaymanIdService paymanIdService) {
        this.paymanIdService = paymanIdService;
    }

    @GetMapping
    public void createCallback(@RequestParam(name = "user_id") String userId,
                               @RequestParam(name = "payman_code") String paymanCode,
                               @RequestParam(name = "status") String creationStatus) throws RuntimeException {

        // TODO: study about factory design pattern
        if (creationStatus.equals(RedirectUrlStatus.CREATED.name())) {
            String paymanId = paymanIdService.getPaymanId(paymanCode);
        } else if (creationStatus.equals(RedirectUrlStatus.UPDATED.name())) {
        } else
            throw new RuntimeException("error");
    }
}
