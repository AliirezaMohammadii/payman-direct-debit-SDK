package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.client.request.ListRequest;
import com.ighe3.api.dto.ListRequestFilter;
import com.ighe3.api.dto.provider.response.PaymanListResponse;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanListRequest;
import com.ighe3.api.service.payman.PaymanGetAllService;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class PaymanGetAllServiceImpl implements PaymanGetAllService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public PaymanGetAllServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public PaymanListResponse getList(ListRequest inputDto) {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = httpService.createRequest(requestBody,
                urls.getPaymanListUrl(),
                httpService.createHeaders(inputDto.getSourceInfo(), accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(request, PaymanGetAllServiceImpl.class);
        return (PaymanListResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanListResponse.class);
    }

    private RequestBody createRequestBody(ListRequest inputDto) {
        PaymanListRequest requestBody = new PaymanListRequest(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBody);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }
}
