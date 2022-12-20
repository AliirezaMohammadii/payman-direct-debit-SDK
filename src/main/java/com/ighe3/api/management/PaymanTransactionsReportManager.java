package com.ighe3.api.management;

import com.ighe3.api.dal.dto.input.PaymanTransactionsReportInputDTO;
import com.ighe3.api.dal.dto.input.PaymanUpdateInputDTO;
import com.ighe3.api.model.paymanRequestBodies.PaymanTransactionsReportRequestBodyObject;
import com.ighe3.api.model.paymanRequestBodies.PaymanUpdateRequestBodyObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PaymanTransactionsReportManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public Response getReport(PaymanTransactionsReportInputDTO inputDto) throws IOException {
        RequestBody body = createRequestBody();
        Request request = getRequest(body, Urls.REPORTS_TRANSACTIONS.getValue(), getHeaders());
        Response response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() {

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
    protected Headers getHeaders() throws IOException {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + paymanService.getAccessToken())
                .build();
        return headers;
    }
}
