package com.payman.api.idk;

import com.payman.api.dto.client.response.TraceCreationResponse;
import com.payman.api.exception.InternalException;
import com.payman.api.exception.enums.ExceptionCodes;
import com.payman.api.service.payman.TraceCreationService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@Component
@Scope("prototype")
public class PaymanCreationTracer implements Runnable {

    public static final Map<String, Future<?>> ALL_TRACERS = new HashMap<>();
    private static final int DELAY_IN_SECONDS = 30;

    private final TraceCreationService traceCreationService;
    private String userId;

    public PaymanCreationTracer(TraceCreationService traceCreationService) {
        this.traceCreationService = traceCreationService;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void run() {

        if (userId == null)
            throw new InternalException(ExceptionCodes.USER_ID_IS_NULL.code);

        while (!Thread.currentThread().isInterrupted()) {

            try {
                Thread.sleep(DELAY_IN_SECONDS);
            } catch (InterruptedException e) {
                return;
            }

            try {
                TraceCreationResponse traceCreationResponse = traceCreationService.trace(userId);

                // TODO: check status and decide what to do.

            } catch (IOException e) {
                throw new InternalException(ExceptionCodes.INTERNAL_EXCEPTION.code);
            }
        }
    }
}

