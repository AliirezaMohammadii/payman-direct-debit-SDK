package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TransactionsReportRequest;
import com.ighe3.api.dto.provider.response.PaymanTransactionsReportResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanTransactionsReportRequest;
import com.ighe3.api.utils.ExceptionTranslator;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class TransactionsReportService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public TransactionsReportService(ExceptionTranslator exceptionTranslator, AccessTokenService accessTokenService, Urls urls) {
        super(exceptionTranslator);
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanTransactionsReportResponse getReport(TransactionsReportRequest inputDto) throws RuntimeException {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getTransactionsReportUrl(), createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = sendRequest(request, TransactionsReportService.class);
        PaymanTransactionsReportResponse paymanResponseBody
                = (PaymanTransactionsReportResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanTransactionsReportResponse.class);
        return paymanResponseBody;
    }

    private RequestBody createRequestBody(TransactionsReportRequest inputDto) throws RuntimeException {
        PaymanTransactionsReportRequest requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanTransactionsReportRequest getRequestBodyObject(TransactionsReportRequest inputDto) {
        PaymanTransactionsReportRequest requestBodyObject = new PaymanTransactionsReportRequest();
        requestBodyObject.setOffset(inputDto.getOffset());
        requestBodyObject.setLength(inputDto.getLength());
        requestBodyObject.setStartDate(inputDto.getStartDate());
        requestBodyObject.setEndDate(inputDto.getEndDate());
        requestBodyObject.setBankCode(inputDto.getBankCode());
        return requestBodyObject;
    }
}
