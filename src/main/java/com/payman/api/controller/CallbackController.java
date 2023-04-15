package com.payman.api.controller;

import com.payman.api.dto.enums.RedirectUrlStatus;
import com.payman.api.exception.PaymanException;
import com.payman.api.service.payman.PaymanIdService;
import com.payman.api.idk.PaymanCreationTracer;
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
                               @RequestParam(name = "error", required = false) String errorCode) {

        // Stop corresponding thread.
        PaymanCreationTracer.ALL_TRACERS.get(userId).cancel(true);

        // TODO: how to handle errorMessage in this situation
        if (errorCode != null) {
            throw new PaymanException(errorCode, null, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        if (status.equals(RedirectUrlStatus.CREATED.name())) {
            // TODO
        } else if (status.equals(RedirectUrlStatus.UPDATED.name())) {
            // TODO
        } else {}
            // TODO
    }
}
