package com.ighe3.api.management.payman;

import com.ighe3.api.dal.dto.payman.input.PaymanTransactionsReportInputDTO;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.payman.requestBody.PaymanTransactionsReportRequestBodyObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class PaymanTransactionsReportManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public ResponseObject getReport(PaymanTransactionsReportInputDTO inputDto) throws Exception {
        RequestBody body = createRequestBody();
        Request request = createRequest(body, Urls.REPORTS_TRANSACTIONS.getValue(), createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() throws Exception {

        PaymanTransactionsReportRequestBodyObject requestBodyObject = getRequestBodyObject();

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanTransactionsReportRequestBodyObject getRequestBodyObject() {
        PaymanTransactionsReportRequestBodyObject requestBodyObject = new PaymanTransactionsReportRequestBodyObject();
        requestBodyObject.setOffset(0);
        requestBodyObject.setLength(0);
        requestBodyObject.setStartDate("...");
        requestBodyObject.setEndDate("...");
        requestBodyObject.setBankCode("...");
        return requestBodyObject;
    }

    @Override
    protected Headers createHeaders() throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + paymanService.getAccessToken())
                .build();
        return headers;
    }
}
