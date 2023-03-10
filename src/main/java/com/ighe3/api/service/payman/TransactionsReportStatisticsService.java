package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.dto.input.PaymanTransactionsReportStatisticsIto;
import com.ighe3.api.model.response.PaymanTransactionsReportStatisticsResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.BaseResponse;
import com.ighe3.api.model.request.PaymanTransactionsReportStatisticsRequest;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class TransactionsReportStatisticsService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public TransactionsReportStatisticsService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanTransactionsReportStatisticsResponse getReportStatistics(PaymanTransactionsReportStatisticsIto inputDto)
            throws Exception {

        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getTransactionsReportStatisticsUrl(), createHeaders());
        BaseResponse paymanResponse = sendRequest(request);
        PaymanTransactionsReportStatisticsResponse paymanResponseBody
                = (PaymanTransactionsReportStatisticsResponse) convertJsonToJavaObject(paymanResponse.getBody());
        return paymanResponseBody;
    }

    private RequestBody createRequestBody(PaymanTransactionsReportStatisticsIto inputDto) throws Exception {

        PaymanTransactionsReportStatisticsRequest requestBodyObject = getRequestBodyObject(inputDto);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanTransactionsReportStatisticsRequest getRequestBodyObject(PaymanTransactionsReportStatisticsIto inputDto) {
        PaymanTransactionsReportStatisticsRequest requestBodyObject
                = new PaymanTransactionsReportStatisticsRequest();
        requestBodyObject.setStartDate(inputDto.getStartDate());
        requestBodyObject.setEndDate(inputDto.getEndDate());
        return requestBodyObject;
    }

    @Override
    protected Headers createHeaders() throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .build();
        return headers;
    }

    @Override
    protected Object convertJsonToJavaObject(String value) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            PaymanTransactionsReportStatisticsResponse response
                    = mapper.readValue(value, PaymanTransactionsReportStatisticsResponse.class);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
