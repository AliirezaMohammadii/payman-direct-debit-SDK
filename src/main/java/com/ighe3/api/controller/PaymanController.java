package com.ighe3.api.controller;


import com.ighe3.api.dto.client.request.*;
import com.ighe3.api.dto.client.response.CreateResponse;
import com.ighe3.api.service.payman.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/paymans")
public class PaymanController {

    private final PaymanCreateService paymanCreateService;
    private final PaymanIdService paymanIdService;
    private final PaymanPayService paymanPayService;
    private final PaymanUpdateService paymanUpdateService;
    private final PaymanChangeStatusService paymanChangeStatusService;
    private final PaymanGetService paymanGetService;
    private final PaymanGetAllService paymanGetAllService;

    public PaymanController(PaymanCreateService paymanCreateService,
                            PaymanIdService paymanIdService,
                            PaymanPayService paymanPayService,
                            PaymanUpdateService paymanUpdateService,
                            PaymanChangeStatusService paymanChangeStatusService,
                            PaymanGetService paymanGetService,
                            PaymanGetAllService paymanGetAllService) {

        this.paymanCreateService = paymanCreateService;
        this.paymanIdService = paymanIdService;
        this.paymanPayService = paymanPayService;
        this.paymanUpdateService = paymanUpdateService;
        this.paymanChangeStatusService = paymanChangeStatusService;
        this.paymanGetService = paymanGetService;
        this.paymanGetAllService = paymanGetAllService;
    }

    @PostMapping
    // TODO: maybe no need to @ResponseStatus(HttpStatus.OK)
    @ResponseStatus(HttpStatus.OK)
    public CreateResponse createPayman(@RequestBody CreateRequest inputDTO) throws RuntimeException {
        return paymanCreateService.create(inputDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Object updatePayman(@RequestBody UpdateRequest inputDTO) throws RuntimeException {
        return paymanUpdateService.update(inputDTO);
    }

    @PutMapping("/change_status")
    @ResponseStatus(HttpStatus.OK)
    public Object changePaymanStatus(@RequestBody ChangeStatusRequest inputDTO) throws RuntimeException {
        return paymanChangeStatusService.changeStatus(inputDTO);
    }

    @GetMapping("/{paymanId}")
    @ResponseStatus(HttpStatus.OK)
    public Object getPayman(@PathVariable String paymanId) throws RuntimeException {
        return paymanGetService.getReport(paymanId);
    }

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Object getPaymans(@RequestBody ListRequest inputDTO) throws RuntimeException {
        return paymanGetAllService.getList(inputDTO);
    }

    @GetMapping("/payman_id/{paymanCode}")
    @ResponseStatus(HttpStatus.OK)
    public Object getPaymanId(@PathVariable String paymanCode) throws RuntimeException {
        return paymanIdService.getPaymanId(paymanCode);
    }

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    public Object pay(@RequestBody PayRequest inputDTO) throws RuntimeException {
        return paymanPayService.pay(inputDTO);
    }
}
