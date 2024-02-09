package com.ighe3.api.controller;


import com.ighe3.api.dto.client.request.*;
import com.ighe3.api.dto.client.response.CreateResponse;
import com.ighe3.api.service.payman.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1/paymans")
public class PaymanController {

    private final CreateService createService;
    private final PaymanIdService paymanIdService;
    private final PayService payService;
    private final PaymanUpdateService paymanUpdateService;
    private final ChangeStatusService changeStatusService;
    private final GetPaymanService getPaymanService;
    private final GetAllPaymansService getAllPaymansService;

    public PaymanController(CreateService createService,
                            PaymanIdService paymanIdService,
                            PayService payService,
                            PaymanUpdateService paymanUpdateService,
                            ChangeStatusService changeStatusService,
                            GetPaymanService getPaymanService,
                            GetAllPaymansService getAllPaymansService) {

        this.createService = createService;
        this.paymanIdService = paymanIdService;
        this.payService = payService;
        this.paymanUpdateService = paymanUpdateService;
        this.changeStatusService = changeStatusService;
        this.getPaymanService = getPaymanService;
        this.getAllPaymansService = getAllPaymansService;
    }

    @PostMapping
    // TODO: maybe no need to @ResponseStatus(HttpStatus.OK)
    @ResponseStatus(HttpStatus.OK)
    public CreateResponse createPayman(@RequestBody CreateRequest request) throws IOException {
        return createService.create(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Object updatePayman(@RequestBody UpdateRequest request) throws IOException {
        return paymanUpdateService.update(request);
    }

    @PutMapping("/change_status")
    @ResponseStatus(HttpStatus.OK)
    public Object changePaymanStatus(@RequestBody ChangeStatusRequest request) throws IOException {
        return changeStatusService.changeStatus(request);
    }

    @GetMapping("/{paymanId}")
    @ResponseStatus(HttpStatus.OK)
    public Object getPayman(@PathVariable String paymanId) {
//        return getPaymanService.get(paymanId);
        return null;
    }

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Object getPaymans(@RequestBody GetAllPaymansRequest request) throws IOException {
        return getAllPaymansService.get(request);
    }

    @GetMapping("/payman_id/{paymanCode}")
    @ResponseStatus(HttpStatus.OK)
    public Object getPaymanId(@PathVariable String paymanCode) {
//        return paymanIdService.getPaymanId(paymanCode);
        return null;
    }

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    public Object pay(@RequestBody PayRequest request) throws IOException {
        return payService.pay(request);
    }
}
