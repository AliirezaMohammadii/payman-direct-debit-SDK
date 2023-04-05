package com.ighe3.api.controller;

import com.ighe3.api.service.payman.TraceCreationService;
import com.ighe3.api.service.payman.TracePaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    public Object traceCreatePayman(HttpServletRequest httpServletRequest,
                                    @PathVariable String traceId) throws IOException {
        return traceCreationService.trace(httpServletRequest, traceId);
    }

    @GetMapping("/payment/{traceId}")
    @ResponseStatus(HttpStatus.OK)
    public Object tracePay(HttpServletRequest httpServletRequest,
                           @PathVariable String traceId,
                           @RequestParam(required = false) String date) throws IOException {
        return tracePaymentService.trace(httpServletRequest, traceId, date);
    }
}
