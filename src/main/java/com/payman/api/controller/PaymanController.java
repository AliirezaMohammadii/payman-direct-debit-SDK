package com.payman.api.controller;


import com.payman.api.dto.client.response.CreateResponse;
import com.payman.api.dto.client.request.*;
import com.payman.api.service.payman.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public CreateResponse createPayman(HttpServletRequest httpServletRequest,
                                       @RequestBody CreateRequest request) throws IOException {
        return createService.create(httpServletRequest, request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Object updatePayman(HttpServletRequest httpServletRequest,
                               @RequestBody UpdateRequest request) throws IOException {
        return paymanUpdateService.update(httpServletRequest, request);
    }

    @PutMapping("/change_status")
    @ResponseStatus(HttpStatus.OK)
    public Object changePaymanStatus(HttpServletRequest httpServletRequest,
                                     @RequestBody ChangeStatusRequest request) throws IOException {
        return changeStatusService.changeStatus(httpServletRequest, request);
    }

    @GetMapping("/{paymanId}")
    @ResponseStatus(HttpStatus.OK)
    public Object getPayman(HttpServletRequest httpServletRequest,
                            @PathVariable String paymanId) throws IOException {
        return getPaymanService.get(httpServletRequest, paymanId);
    }

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Object getPaymans(HttpServletRequest httpServletRequest,
                             @RequestBody GetAllPaymansRequest request) throws IOException {
        return getAllPaymansService.get(httpServletRequest, request);
    }

    @GetMapping("/payman_id/{paymanCode}")
    @ResponseStatus(HttpStatus.OK)
    public Object getPaymanId(HttpServletRequest httpServletRequest,
                              @PathVariable String paymanCode) throws IOException {
        return paymanIdService.getPaymanId(httpServletRequest, paymanCode);
    }

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    public Object pay(HttpServletRequest httpServletRequest,
                      @RequestBody PayRequest request) throws IOException {
        return payService.pay(httpServletRequest, request);
    }
}
