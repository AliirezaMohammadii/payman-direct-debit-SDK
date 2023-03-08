package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.dto.input.PaymanTransactionsReportIto;
import com.ighe3.api.model.response.PaymanTransactionsReportResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.dto.BaseResponse;
import com.ighe3.api.model.request.PaymanTransactionsReportRequest;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class TransactionsReportService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public TransactionsReportService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanTransactionsReportResponse getReport(PaymanTransactionsReportIto inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getTransactionsReportUrl(), createHeaders());
        BaseResponse paymanResponse = sendRequest(request);
        PaymanTransactionsReportResponse paymanResponseBody
                = (PaymanTransactionsReportResponse) convertJsonToJavaObject(paymanResponse.getBody());
        return paymanResponseBody;
    }

    private RequestBody createRequestBody(PaymanTransactionsReportIto inputDto) throws Exception {

        PaymanTransactionsReportRequest requestBodyObject = getRequestBodyObject(inputDto);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanTransactionsReportRequest getRequestBodyObject(PaymanTransactionsReportIto inputDto) {
        PaymanTransactionsReportRequest requestBodyObject = new PaymanTransactionsReportRequest();
        requestBodyObject.setOffset(inputDto.getOffset());
        requestBodyObject.setLength(inputDto.getLength());
        requestBodyObject.setStartDate(inputDto.getStartDate());
        requestBodyObject.setEndDate(inputDto.getEndDate());
        requestBodyObject.setBankCode(inputDto.getBankCode());
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
            PaymanTransactionsReportResponse response = mapper.readValue(value, PaymanTransactionsReportResponse.class);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
