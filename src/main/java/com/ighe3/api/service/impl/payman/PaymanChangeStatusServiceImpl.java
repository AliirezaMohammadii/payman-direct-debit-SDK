package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.client.request.ChangeStatusRequest;
import com.ighe3.api.dto.provider.response.PaymanChangeStatusResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.payman.PaymanChangeStatusService;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanChangeStatusRequest;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class PaymanChangeStatusServiceImpl implements PaymanChangeStatusService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public PaymanChangeStatusServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public PaymanChangeStatusResponse changeStatus(ChangeStatusRequest inputDto) {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = httpService.createRequest(requestBody, urls.getChangeStatusUrl(), httpService.createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = httpService.sendRequest(request, PaymanChangeStatusServiceImpl.class);
        return (PaymanChangeStatusResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanChangeStatusResponse.class);
    }

    // TODO: move this method to httpService and pass classType as an method argument.
    private RequestBody createRequestBody(ChangeStatusRequest inputDto) throws RuntimeException {
        PaymanChangeStatusRequest requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }

    private PaymanChangeStatusRequest getRequestBodyObject(ChangeStatusRequest inputDto) {
        PaymanChangeStatusRequest requestBodyObject = new PaymanChangeStatusRequest();
        requestBodyObject.setNewStatus(inputDto.getNewStatus());
        requestBodyObject.setPaymanId(inputDto.getPaymanId());
        return requestBodyObject;
    }
}
