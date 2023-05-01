package com.payman.api.idk;

import com.payman.api.dto.client.response.GetPaymanIdResponse;
import com.payman.api.dto.client.response.TraceCreationResponse;
import com.payman.api.dto.enums.PaymanStatus;
import com.payman.api.exception.InternalException;
import com.payman.api.exception.PaymanException;
import com.payman.api.exception.enums.ExceptionCodes;
import com.payman.api.service.payman.PaymanIdService;
import com.payman.api.service.payman.TraceCreationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@Component
@Scope("prototype")
@Slf4j
public class PaymanCreationTracer implements Runnable {

    public static final Map<String, Future<?>> ALL_TRACERS = new HashMap<>();
    private static final int DELAY_IN_SECONDS = 15;
    private static final int TIME_LIMIT_IN_MINUTES = 10;

    private final TraceCreationService traceCreationService;
    private final PaymanIdService paymanIdService;

    private String traceId;
    private String userId;
    private boolean stop;
    private LocalDateTime startTime;

    public PaymanCreationTracer(TraceCreationService traceCreationService, PaymanIdService paymanIdService) {
        this.traceCreationService = traceCreationService;
        this.paymanIdService = paymanIdService;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void run() {

        startTime = LocalDateTime.now();

        log.info("tracer . thread started to work; with trace id: {}", this.traceId);

        checkNeededParametersNullability();

        int ctr = 0;
        while (!Thread.currentThread().isInterrupted() && !stop) {

            log.info("tracer . counting . trace id:{}, counter:{}", this.traceId, ctr++);

            if (timeLimitIsPassed())
                break;

            sleep();
            if (stop)
                break;

            try {
                TraceCreationResponse traceCreationResponse = traceCreationService.trace(traceId);
                checkPaymanCreationStatus(traceCreationResponse);
            }

            /**
             2023/4/26
             If status is "INITIALIZING", exception raises with code: 2014.
             INITIALIZING status means that user is still on consent page, or left consent page url alone.
             */ catch (PaymanException ex) {
                log.info("tracer . status is INITIALIZING . in catcher");
            } catch (IOException ex) {
                throw new InternalException(ExceptionCodes.UNKNOWN_EXCEPTION);
            }
        }
    }

    private void checkNeededParametersNullability() {
        if (userId == null)
            throw new InternalException(ExceptionCodes.USER_ID_IS_NULL_WHILE_CREATING_TRACER);

        if (traceId == null)
            throw new InternalException(ExceptionCodes.TRACE_ID_IS_NULL_WHILE_CREATING_TRACER);
    }

    private boolean timeLimitIsPassed() {
        LocalDateTime now = LocalDateTime.now();
        return now.compareTo(startTime.plusMinutes(TIME_LIMIT_IN_MINUTES)) > 0;
    }

    private void sleep() {
        try {
            Thread.sleep(DELAY_IN_SECONDS * 1000);
        } catch (InterruptedException e) {
            log.error("tracer . interrupt!");
            stop = true;
        }
    }

    /**
     * 2023/4/30
     * If status is "INITIALIZING", it means that user is still on consent page, or left consent page url alone.
     * Currently, this case is handled by returning error code from faraboom and raising exception by app;
     * but I also handle this case in this way, for future changes.
     * <p>
     * 2023/4/26
     * If status is "WAITING FOR CONFIRM", it means that user has passed consent page,
     * and now "payman code" is present, to use for getting "payman id" by calling "getPaymanIdService".
     * So in "traceCreationResponse", "payman code" has value, and "payman id" is null.

     * 2023/4/30
     * I don't check other statuses. Why?
     * "ACTIVE": Logically, in this case, callback method is called before, and before calling getPaymanIdService by callback method,
     * the current thread is killed by callback method.
     * "EXPIRED", "DEACTIVE": These cases are not related to creating payman, and we won't face them.

     * 2023/4/30
     * If tracer reaches to "WAITING_FOR_CONFIRM" or "CANCELED" cases, it means that callback function hasn't worked correctly.
     */
    private void checkPaymanCreationStatus(TraceCreationResponse traceCreationResponse) throws IOException {
        String paymanStatus = traceCreationResponse.getStatus();

        switch (PaymanStatus.valueOf(paymanStatus)) {
            case INITIALIZING:
                log.info("tracer . status is INITIALIZING");
                break;

            case WAITING_FOR_CONFIRM:
                log.info("tracer . status is WAITING_FOR_CONFIRM");
                log.info("tracer . payman code:{}", traceCreationResponse.getPaymanCode());
                GetPaymanIdResponse response = paymanIdService.getPaymanId(traceCreationResponse.getPaymanCode());
                String paymanId = response.getPaymanId();
                log.info("tracer . payman id : {}", paymanId);
                log.info("tracer . thread is stopping...");
                // Find user by "user_id" and save payman id to db.
                stop = true;
                break;

            case CANCELLED:
                log.info("tracer . status is CANCELED");
                stop = true;
                break;

            default:
                log.info("tracer . status is UNKNOWN . status : {}", traceCreationResponse.getStatus());
                throw new InternalException(ExceptionCodes.UNKNOWN_EXCEPTION);
        }
    }
}

