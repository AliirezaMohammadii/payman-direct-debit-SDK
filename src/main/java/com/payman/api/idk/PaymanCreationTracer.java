package com.payman.api.idk;

import com.payman.api.dto.client.response.GetPaymanIdResponse;
import com.payman.api.dto.client.response.TraceCreationResponse;
import com.payman.api.exception.InternalException;
import com.payman.api.exception.PaymanException;
import com.payman.api.exception.enums.ExceptionCodes;
import com.payman.api.service.payman.PaymanIdService;
import com.payman.api.service.payman.TraceCreationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Future;

@Component
@Scope("prototype")
@Slf4j
public class PaymanCreationTracer implements Runnable {

    public static final Map<String, Future<?>> ALL_TRACERS = new HashMap<>();
    private static final int DELAY_IN_SECONDS = 30;

    private final TraceCreationService traceCreationService;
    private final PaymanIdService paymanIdService;

    private String traceId;

    public PaymanCreationTracer(TraceCreationService traceCreationService, PaymanIdService paymanIdService) {
        this.traceCreationService = traceCreationService;
        this.paymanIdService = paymanIdService;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    @Override
    public void run() {

        log.info("");
        log.info("\t-------\t-------\t-------\t-------");
        log.info("\t\tthread started to work, with trace id: {}", this.traceId);
        log.info("\t-------\t-------\t-------\t-------");
        log.info("");

        if (traceId == null)
            throw new InternalException(ExceptionCodes.USER_ID_IS_NULL);

        int ctr = 0;
        while (!Thread.currentThread().isInterrupted()) {

            log.info("trace id:{}, counter:{}", this.traceId, ctr++);

            try {
                Thread.sleep(DELAY_IN_SECONDS*1000);
            } catch (InterruptedException e) {
                log.error("interrupttttt!");
                return;
            }

            try {
                log.info("in tracer thread. calling traceCreationService");
                TraceCreationResponse traceCreationResponse = traceCreationService.trace(traceId);
                log.info("in thread. payman code:{}", traceCreationResponse.getPaymanCode());

                /**
                 * If status is "WAITING FOR CONFIRM", it means that user has passed consent page,
                 * and now "payman code" is present, to use for getting "payman id" by calling "getPaymanIdService".
                 * So in "traceCreationResponse", "payman code" has value, and "payman id" is null.
                 *
                 * If status is "ACTIVE", it means that "getPaymanIdService" is called using "payman code" and
                 * "payman id" has been provided.
                 * So in "traceCreationResponse", "payman id" has value, and "payman code" is null.
                 */
                if (!Objects.isNull(traceCreationResponse.getPaymanCode())) {
                    GetPaymanIdResponse response = paymanIdService.getPaymanId(traceCreationResponse.getPaymanCode());
                    String paymanId = response.getPaymanId();
                    // save payman id to db
                    return;
                }

                // LAST POINT
                else if (!Objects.isNull(traceCreationResponse.getPaymanId()))
                /**
                 * "payman id" is got before, and storing operation is done before, in callback function; so now do nothing and kill the thread.
                 */
                    return;

                else
                    throw new InternalException(ExceptionCodes.UNKNOWN_EXCEPTION);

            }

            /**
             * If status is "INITIALIZING", exception raises with code: 2014.
             * INITIALIZING status means that user is still on consent page, or left consent page url alone.
             */
            catch (PaymanException ex) {
                log.error("\n\terror code:{}\n\terror message:{}\n\terror info:{}", ex.getCode(), ex.getMessage(), ex.getInfo());
            }
            catch (IOException ex) {
                throw new InternalException(ExceptionCodes.UNKNOWN_EXCEPTION);
            }
        }
    }
}

