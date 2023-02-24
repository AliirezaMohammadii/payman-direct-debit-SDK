package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.dal.dto.input.TransactionsReportStatisticsInputDto;
import com.ighe3.api.model.response.PaymanTransactionsReportStatisticsResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseModel;
import com.ighe3.api.model.request.PaymanTransactionsReportStatisticsRequest;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class TransactionsReportStatisticsService extends BaseService {
    private final AccessTokenService accessTokenService;

    public TransactionsReportStatisticsService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public Object getReportStatistics(TransactionsReportStatisticsInputDto inputDto) throws Exception {
        ResponseModel paymanResponse = getResponseObject(inputDto);
        PaymanTransactionsReportStatisticsResponse paymanResponseBody
                = (PaymanTransactionsReportStatisticsResponse) convertJsonToJavaObject(paymanResponse.getBody());
        return null;
    }

    private ResponseModel getResponseObject(TransactionsReportStatisticsInputDto inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, Urls.TRANSACTIONS_REPORT_STATISTICS.getValue(), createHeaders());
        ResponseModel response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody(TransactionsReportStatisticsInputDto inputDto) throws Exception {

        PaymanTransactionsReportStatisticsRequest requestBodyObject = getRequestBodyObject(inputDto);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanTransactionsReportStatisticsRequest getRequestBodyObject(TransactionsReportStatisticsInputDto inputDto) {
        PaymanTransactionsReportStatisticsRequest requestBodyObject
                = new PaymanTransactionsReportStatisticsRequest();
        requestBodyObject.setStartDate(null);
        requestBodyObject.setEndDate(null);
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
