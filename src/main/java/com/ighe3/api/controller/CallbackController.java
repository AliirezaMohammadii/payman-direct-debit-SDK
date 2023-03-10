package com.ighe3.api.controller;

import com.ighe3.api.model.enums.RedirectUrlStatuses;
import com.ighe3.api.service.payman.GetPaymanIdService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callback")
public class CallbackController {

    private final GetPaymanIdService getPaymanIdService;

    public CallbackController(GetPaymanIdService getPaymanIdService) {
        this.getPaymanIdService = getPaymanIdService;
    }

    @GetMapping("/create")
    public void createCallback(@RequestParam(name = "user_id") String userId,
                               @RequestParam(name = "payman_code") String paymanCode,
                               @RequestParam(name = "status") String creationStatus) throws RuntimeException {

        if (creationStatus.equals(RedirectUrlStatuses.CREATED.name())) {
            String paymanId = getPaymanIdService.getPaymanId(paymanCode);
        } else if (creationStatus.equals(RedirectUrlStatuses.UPDATED.name())) {
        } else
            throw new RuntimeException("error");
    }
}
