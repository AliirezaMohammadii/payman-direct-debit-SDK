package com.ighe3.api.controller;

import com.ighe3.api.dto.enums.RedirectUrlStatus;
import com.ighe3.api.exception.PaymanException;
import com.ighe3.api.service.payman.PaymanIdService;
import org.springframework.http.HttpStatus;
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
    public void handleCallback(@RequestParam(name = "user_id") String userId,
                               @RequestParam(name = "payman_code") String paymanCode,
                               @RequestParam(name = "status") String status,
                               // TODO: I'm not sure about "error" keyword
                               @RequestParam(name = "error", required = false) String errorCode) throws RuntimeException {

        // TODO: about errorMessage
        if (errorCode != null) {
            throw new PaymanException(errorCode, null, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        // TODO: study about factory design pattern
        if (status.equals(RedirectUrlStatus.CREATED.name())) {
            String paymanId = paymanIdService.getPaymanId(paymanCode);
            // Store paymanId in database

        } else if (status.equals(RedirectUrlStatus.UPDATED.name())) {
        } else
            throw new PaymanException(null, null, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
