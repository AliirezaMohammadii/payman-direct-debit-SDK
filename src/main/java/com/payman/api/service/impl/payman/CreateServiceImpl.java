package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.request.CreateRequest;
import com.payman.api.dto.client.response.CreateResponse;
import com.payman.api.idk.PaymanCreationTracer;
import com.payman.api.mapper.RequestMapper;
import com.payman.api.service.HttpService;
import com.payman.api.dto.provider.response.CustomizedResponse;
import com.payman.api.dto.provider.request.PaymanCreateRequest;
import com.payman.api.service.payman.AccessTokenService;
import com.payman.api.service.payman.CreateService;
import com.payman.api.utils.CustomHttpHeaders;
import okhttp3.*;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.Future;

@Service
public class CreateServiceImpl implements CreateService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenService accessTokenService;
    private final ThreadPoolTaskExecutor taskExecutor;
    private final ObjectProvider<PaymanCreationTracer> objectProvider;

    public CreateServiceImpl(HttpService httpService,
                             UrlPropertiesConfig urlPropertiesConfig,
                             AccessTokenService accessTokenService,
                             ThreadPoolTaskExecutor taskExecutor,
                             ObjectProvider<PaymanCreationTracer> objectProvider) {

        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
        this.taskExecutor = taskExecutor;
        this.objectProvider = objectProvider;
    }

    @Override
    public CreateResponse create(HttpServletRequest httpServletRequest, CreateRequest request) throws IOException {
        appendUserIdAndTraceIdToRedirectUrlAsQueryParam(request);
        RequestBody requestBody = RequestMapper.map(request, CreateRequest.class, PaymanCreateRequest.class);

        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getCreate(),
                createHeaders(httpServletRequest, request));

        CustomizedResponse paymanCustomizedResponse = httpService.sendRequest(paymanRequest, CreateService.class);
        Headers headers = paymanCustomizedResponse.getHeaders();
        traceCreationStatus(request);
        return new CreateResponse(headers.get(HttpHeaders.LOCATION));
    }

    private void appendUserIdAndTraceIdToRedirectUrlAsQueryParam(CreateRequest request) {
        String redirectUrl = request.getRedirectUrl();
        redirectUrl = String.format("%s?user_id=%s&trace_id=%s", redirectUrl, request.getPayman().getUserId(), request.getTraceId());
        request.setRedirectUrl(redirectUrl);
    }

    private void traceCreationStatus(CreateRequest request) {
        PaymanCreationTracer tracer = objectProvider.getObject();
        tracer.setTraceId(request.getTraceId());
        tracer.setUserId(request.getPayman().getUserId());
        Future<?> future = taskExecutor.submit(tracer);
        PaymanCreationTracer.ALL_TRACERS.put(request.getTraceId(), future);
    }

    private Headers createHeaders(HttpServletRequest httpServletRequest, CreateRequest request) {
        Headers generalHeaders = httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken());
        return generalHeaders.newBuilder()
                .add(CustomHttpHeaders.MOBILE_NO, request.getMobileNumber())
                .add(CustomHttpHeaders.NATIONAL_CODE, request.getNationalCode())
                .build();
    }
}
