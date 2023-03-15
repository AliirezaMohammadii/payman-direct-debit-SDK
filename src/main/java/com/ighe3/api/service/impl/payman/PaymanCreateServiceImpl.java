package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.client.request.CreateRequest;
import com.ighe3.api.dto.client.response.CreateResponse;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanCreateRequest;
import com.ighe3.api.service.payman.PaymanCreateService;
import com.ighe3.api.utils.*;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class PaymanCreateServiceImpl implements PaymanCreateService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public PaymanCreateServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public CreateResponse create(CreateRequest inputDto) {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = httpService.createRequest(requestBody, urls.getCreateUrl(), createHeaders(inputDto));
        Response paymanResponse = httpService.sendRequest(request, PaymanCreateService.class);
        Headers headers = paymanResponse.getHeaders();
        return new CreateResponse(headers.get("Location"));
    }

    private RequestBody createRequestBody(CreateRequest inputDto) {
        PaymanCreateRequest requestBody = new PaymanCreateRequest(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBody);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }

    private Headers createHeaders(CreateRequest inputDto) {
        Headers generalHeaders = httpService.createHeaders(inputDto.getSourceInfo(), accessTokenService.getAccessToken());
        Headers headers = generalHeaders.newBuilder()
                .add(CustomHttpHeaders.MOBILE_NO, inputDto.getMobileNumber())
                .add(CustomHttpHeaders.NATIONAL_CODE, inputDto.getNationalCode())
                .build();
        return headers;
    }
}
