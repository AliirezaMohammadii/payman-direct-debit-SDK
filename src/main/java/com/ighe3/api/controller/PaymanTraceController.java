package com.ighe3.api.controller;

import com.ighe3.api.service.payman.TraceCreationService;
import com.ighe3.api.service.payman.TracePaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payman/traces")
public class PaymanTraceController {

    private final TraceCreationService traceCreationService;
    private final TracePaymentService tracePaymentService;

    public PaymanTraceController(TraceCreationService traceCreationService, TracePaymentService tracePaymentService) {
        this.traceCreationService = traceCreationService;
        this.tracePaymentService = tracePaymentService;
    }

    @GetMapping("/creation/{traceId}")
    @ResponseStatus(HttpStatus.OK)
    public Object traceCreatePayman(@PathVariable String traceId) {
        return traceCreationService.trace(traceId);
    }

    @GetMapping("/payment/{traceId}")
    @ResponseStatus(HttpStatus.OK)
    public Object tracePay(@PathVariable String traceId,
                           @RequestParam(required = false) String date) {
        return tracePaymentService.trace(traceId, date);
    }
}
