package com.ighe3.api.service.payman;

import com.ighe3.api.dto.input.PaymanTransactionsReportIto;
import com.ighe3.api.model.response.PaymanTransactionsReportResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.CustomizedResponse;
import com.ighe3.api.model.request.PaymanTransactionsReportRequest;
import com.ighe3.api.utils.ExceptionTranslator;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
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

    public PaymanTransactionsReportResponse getReport(PaymanTransactionsReportIto inputDto) throws RuntimeException {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getTransactionsReportUrl(), createHeaders());
        CustomizedResponse paymanResponse = sendRequest(request, TransactionsReportService.class);
        PaymanTransactionsReportResponse paymanResponseBody
                = (PaymanTransactionsReportResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanTransactionsReportResponse.class);
        return paymanResponseBody;
    }

    private RequestBody createRequestBody(PaymanTransactionsReportIto inputDto) throws RuntimeException {

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
    protected Headers createHeaders() throws RuntimeException {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .build();
        return headers;
    }
}
