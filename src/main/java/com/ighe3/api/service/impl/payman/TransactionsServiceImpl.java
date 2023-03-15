package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.client.request.TransactionsRequest;
import com.ighe3.api.dto.provider.response.PaymanTransactionsResponse;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.TransactionsRequestFilter;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanTransactionsRequest;
import com.ighe3.api.service.payman.TransactionsService;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public TransactionsServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public PaymanTransactionsResponse getTransactions(TransactionsRequest inputDto) {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = httpService.createRequest(requestBody,
                urls.getTransactionsUrl(),
                httpService.createHeaders(inputDto.getSourceInfo(), accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(request, TransactionsServiceImpl.class);
        return (PaymanTransactionsResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanTransactionsResponse.class);
    }

    private RequestBody createRequestBody(TransactionsRequest inputDto) {
        PaymanTransactionsRequest requestBody = new PaymanTransactionsRequest(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBody);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }
}
