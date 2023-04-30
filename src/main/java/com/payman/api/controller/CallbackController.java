package com.payman.api.controller;

import com.payman.api.dto.client.response.GetPaymanIdResponse;
import com.payman.api.dto.enums.RedirectUrlStatus;
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

    @GetMapping
    public void handleCallback(@RequestParam(name = "user_id") String userId,
                               @RequestParam(name = "trace_id") String traceId,
                               @RequestParam(name = "payman_code") String paymanCode,
                               @RequestParam(name = "status") String status,
                               @RequestParam(name = "code", required = false) String errorCode) throws IOException {

        log.info("callback . function is called");
        if (randomReturn())
            return;

        killPaymanCreationTracerThread(traceId);

        if (errorCode != null)
            handleError(status);

        else if (status.equals(RedirectUrlStatus.CREATED.name())) {
            GetPaymanIdResponse response = paymanIdService.getPaymanId(paymanCode);
            log.info("callback . payman id:{}", response.getPaymanId());
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

    private boolean randomReturn() {
        Random random = new Random();
        return random.nextInt(100) != 0;
    }

    private static void killPaymanCreationTracerThread(String traceId) {
        log.info("callback . stopping tracer");
        if (PaymanCreationTracer.ALL_TRACERS.containsKey(traceId))
            PaymanCreationTracer.ALL_TRACERS.get(traceId).cancel(true);
    }

    private static void handleError(String status) {

        log.info("callback . error occurred");

        String exceptionCode;

        switch (RedirectUrlStatus.valueOf(status)) {
            case CANCELED:
                log.info("callback . CANCELED");
                exceptionCode = ExceptionCodes.USER_CANCELED_PAYMAN_CREATION;
                break;
            case TIMEOUT:
                log.info("callback . TIMEOUT");
                exceptionCode = ExceptionCodes.PAYMAN_CREATION_TIMEOUT;
                break;
            case INTERNAL_ERROR:
            default:
                log.info("callback . INTERNAL_ERROR . {}", status);
                exceptionCode = ExceptionCodes.UNKNOWN_ERROR_IN_PAYMAN_CREATION;
                break;
        }

        throw new PaymanException(exceptionCode, HttpStatus.OK.value(), null);
    }
}
