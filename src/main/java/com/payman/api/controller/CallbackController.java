package com.payman.api.controller;

import com.payman.api.dto.client.response.GetPaymanIdResponse;
import com.payman.api.dto.enums.ConsentPageStatus;
import com.payman.api.exception.InternalException;
import com.payman.api.exception.PaymanException;
import com.payman.api.exception.enums.ExceptionCodes;
import com.payman.api.service.payman.PaymanIdService;
import com.payman.api.idk.PaymanCreationTracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping("/v1/callbacks")
@Slf4j
public class CallbackController {

    private final PaymanIdService paymanIdService;

    public CallbackController(PaymanIdService paymanIdService) {
        this.paymanIdService = paymanIdService;
    }

    @GetMapping("/creation")
    public void handleCreationCallback(@RequestParam(name = "user_id") String userId,
                                       @RequestParam(name = "trace_id") String traceId,
                                       @RequestParam(name = "payman_code") String paymanCode,
                                       @RequestParam(name = "status") String status,
                                       @RequestParam(name = "code", required = false) String errorCode) throws IOException {

//        if (randomReturn())
//            return;

        killPaymanCreationTracerThread(traceId);

        if (errorCode != null)
            handleError(status);

        else if (status.equals(ConsentPageStatus.CREATED.name())) {
            GetPaymanIdResponse response = paymanIdService.getPaymanId(paymanCode);
            // find user by "user_id" and save payman id to db.
            /**
             2023/4/30
             about storing payman id to db:
             In an exceptional condition, tracer thread may be stored payman id, a bit before be killed by callback method.
             So check if payman id is not already stored in db.
             */

        } else
            throw new InternalException(ExceptionCodes.UNKNOWN_EXCEPTION);
    }

    @GetMapping("/update")
    public void handleUpdateCallback(@RequestParam(name = "user_id") String userId,
                                     @RequestParam(name = "payman_code") String paymanCode,
                                     @RequestParam(name = "status") String status,
                                     @RequestParam(name = "code", required = false) String errorCode) {

        if (errorCode != null)
            handleError(status);

        else if (status.equals(ConsentPageStatus.UPDATED.name())) {
            // find user by "user_id" and update payman.

        } else
            throw new InternalException(ExceptionCodes.UNKNOWN_EXCEPTION);
    }

    private static void killPaymanCreationTracerThread(String traceId) {
        if (PaymanCreationTracer.ALL_TRACERS.containsKey(traceId))
            PaymanCreationTracer.ALL_TRACERS.get(traceId).cancel(true);
    }

    private static void handleError(String status) {
        String exceptionCode;

        switch (ConsentPageStatus.valueOf(status)) {
            case CANCELED:
                exceptionCode = ExceptionCodes.USER_CANCELED_CONSENT;
                break;
            case TIMEOUT:
                exceptionCode = ExceptionCodes.CONSENT_TIMEOUT;
                break;
            case INTERNAL_ERROR:
            default:
                exceptionCode = ExceptionCodes.CONSENT_UNKNOWN_ERROR;
                break;
        }

        throw new PaymanException(exceptionCode, HttpStatus.OK.value(), null);
    }

    // FOR TEST
    private boolean randomReturn() {
        Random random = new Random();
        return random.nextInt(100) != 0;
    }
}
