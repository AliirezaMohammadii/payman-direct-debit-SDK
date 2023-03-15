package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.client.request.TransactionsReportRequest;
import com.ighe3.api.dto.provider.response.PaymanTransactionsReportResponse;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanTransactionsReportRequest;
import com.ighe3.api.service.payman.TransactionsReportService;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class TransactionsReportServiceImpl implements TransactionsReportService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public TransactionsReportServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public PaymanTransactionsReportResponse getReport(TransactionsReportRequest inputDto) {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = httpService.createRequest(requestBody,
                urls.getTransactionsReportUrl(),
                httpService.createHeaders(inputDto.getSourceInfo(), accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(request, TransactionsReportServiceImpl.class);
        return (PaymanTransactionsReportResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanTransactionsReportResponse.class);
    }

    private RequestBody createRequestBody(TransactionsReportRequest inputDto) {
        PaymanTransactionsReportRequest requestBody = new PaymanTransactionsReportRequest(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBody);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }
}
