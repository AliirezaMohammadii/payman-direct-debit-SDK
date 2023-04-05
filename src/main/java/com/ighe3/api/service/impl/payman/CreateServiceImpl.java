package com.ighe3.api.service.impl.payman;

import com.ighe3.api.config.UrlPropertiesConfig;
import com.ighe3.api.dto.client.request.CreateRequest;
import com.ighe3.api.dto.client.response.CreateResponse;
import com.ighe3.api.idk.PaymanCreationTracer;
import com.ighe3.api.mapper.RequestMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanCreateRequest;
import com.ighe3.api.service.payman.CreateService;
import com.ighe3.api.utils.*;
import okhttp3.*;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.Future;

@Service
public class CreateServiceImpl implements CreateService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenServiceImpl accessTokenService;
    private final ThreadPoolTaskExecutor taskExecutor;
    private final PaymanCreationTracer paymanCreationTracer;

    public CreateServiceImpl(HttpService httpService,
                             UrlPropertiesConfig urlPropertiesConfig,
                             AccessTokenServiceImpl accessTokenService,
                             ThreadPoolTaskExecutor taskExecutor,
                             PaymanCreationTracer paymanCreationTracer) {

        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
        this.taskExecutor = taskExecutor;
        this.paymanCreationTracer = paymanCreationTracer;
    }

    @Override
    public CreateResponse create(HttpServletRequest httpServletRequest, CreateRequest request) throws IOException {
        RequestBody requestBody = RequestMapper.mapRequest(request, CreateRequest.class, PaymanCreateRequest.class);

        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getCreate(),
                createHeaders(httpServletRequest, request));

        Response paymanResponse = httpService.sendRequest(paymanRequest, CreateService.class);
        Headers headers = paymanResponse.getHeaders();
        traceCreationStatus(request);
        return new CreateResponse(headers.get("Location"));
    }

    private void traceCreationStatus(CreateRequest request) {
        paymanCreationTracer.setUserId(request.getPayman().getUserId());
        Future<?> future = taskExecutor.submit(paymanCreationTracer);
        PaymanCreationTracer.ALL_TRACERS.put(request.getPayman().getUserId(), future);
    }

    private Headers createHeaders(HttpServletRequest httpServletRequest, CreateRequest request) {
        Headers generalHeaders = httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken());
        Headers headers = generalHeaders.newBuilder()
                .add(CustomHttpHeaders.MOBILE_NO, request.getMobileNumber())
                .add(CustomHttpHeaders.NATIONAL_CODE, request.getNationalCode())
                .build();
        return headers;
    }
}
