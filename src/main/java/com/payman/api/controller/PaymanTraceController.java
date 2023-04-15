package com.payman.api.controller;

import com.payman.api.dto.client.response.TraceCreationResponse;
import com.payman.api.dto.client.response.TracePaymentResponse;
import com.payman.api.service.payman.TraceCreationService;
import com.payman.api.service.payman.TracePaymentService;
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
    public TraceCreationResponse traceCreatePayman(HttpServletRequest httpServletRequest,
                                                   @PathVariable String traceId) throws IOException {
        return traceCreationService.trace(httpServletRequest, traceId);
    }

    @GetMapping("/payment/{traceId}")
    public TracePaymentResponse tracePay(HttpServletRequest httpServletRequest,
                                         @PathVariable String traceId,
                                         @RequestParam(required = false) String date) throws IOException {
        return tracePaymentService.trace(httpServletRequest, traceId, date);
    }
}
