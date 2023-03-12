package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.client.request.TransactionsReportStatisticsRequest;
import com.ighe3.api.dto.provider.response.PaymanTransactionsReportStatisticsResponse;
import com.ighe3.api.exception.BaseException;
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
            throws BaseException {

        RequestBody requestBody = createRequestBody(inputDto);
        Request request = httpService.createRequest(requestBody, urls.getTransactionsReportStatisticsUrl(), httpService.createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = httpService.sendRequest(request, TransactionsReportStatisticsServiceImpl.class);
        return (PaymanTransactionsReportStatisticsResponse) HttpResponseMapper
                .convertJsonToJavaObject(paymanResponse.getBody(), PaymanTransactionsReportStatisticsResponse.class);
    }

    private RequestBody createRequestBody(TransactionsReportStatisticsRequest inputDto) throws RuntimeException {
        PaymanTransactionsReportStatisticsRequest requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }

    private PaymanTransactionsReportStatisticsRequest getRequestBodyObject(TransactionsReportStatisticsRequest inputDto) {
        PaymanTransactionsReportStatisticsRequest requestBodyObject
                = new PaymanTransactionsReportStatisticsRequest();
        requestBodyObject.setStartDate(inputDto.getStartDate());
        requestBodyObject.setEndDate(inputDto.getEndDate());
        return requestBodyObject;
    }
}
