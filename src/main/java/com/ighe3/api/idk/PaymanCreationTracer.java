package com.ighe3.api.idk;

import com.ighe3.api.dto.client.response.TraceCreationResponse;
import com.ighe3.api.exception.InternalException;
import com.ighe3.api.exception.enums.ExceptionCodes;
import com.ighe3.api.service.payman.TraceCreationService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

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

        delay();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                TraceCreationResponse traceCreationResponse = traceCreationService.trace(userId);

                // TODO: if status is CREATED, save in database and return

            } catch (IOException e) {
                throw new InternalException(ExceptionCodes.INTERNAL_EXCEPTION.code);
            }

            delay();
        }
    }

    private void delay() {
        IntStream.range(0, DELAY_IN_SECONDS).forEach(i -> {
            try {
                if (Thread.currentThread().isInterrupted())
                    return;
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new InternalException(ExceptionCodes.THREAD_SLEEP_INTERRUPTED.code);
            }
        });
    }
}
