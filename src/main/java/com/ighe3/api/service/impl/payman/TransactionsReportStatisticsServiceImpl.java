package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.client.request.TransactionsReportStatisticsRequest;
import com.ighe3.api.dto.provider.response.PaymanTransactionsReportStatisticsResponse;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanTransactionsReportStatisticsRequest;
import com.ighe3.api.service.payman.TransactionsReportStatisticsService;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class TransactionsReportStatisticsServiceImpl implements TransactionsReportStatisticsService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public TransactionsReportStatisticsServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public PaymanTransactionsReportStatisticsResponse getReportStatistics(TransactionsReportStatisticsRequest inputDto)
            {

        RequestBody requestBody = createRequestBody(inputDto);
        Request request = httpService.createRequest(requestBody,
                urls.getTransactionsReportStatisticsUrl(),
                httpService.createHeaders(inputDto.getSourceInfo(), accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(request, TransactionsReportStatisticsServiceImpl.class);
        return (PaymanTransactionsReportStatisticsResponse) HttpResponseMapper
                .convertJsonToJavaObject(paymanResponse.getBody(), PaymanTransactionsReportStatisticsResponse.class);
    }

    private RequestBody createRequestBody(TransactionsReportStatisticsRequest inputDto) {
        PaymanTransactionsReportStatisticsRequest requestBody = new PaymanTransactionsReportStatisticsRequest(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBody);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }
}
