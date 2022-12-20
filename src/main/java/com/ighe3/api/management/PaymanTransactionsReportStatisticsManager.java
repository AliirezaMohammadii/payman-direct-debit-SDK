package com.ighe3.api.management;

import com.ighe3.api.dal.dto.input.PaymanTransactionsReportInputDTO;
import com.ighe3.api.dal.dto.input.PaymanTransactionsReportStatisticsInputDTO;
import com.ighe3.api.model.paymanRequestBodies.PaymanTransactionsReportRequestBodyObject;
import com.ighe3.api.model.paymanRequestBodies.PaymanTransactionsReportStatisticsRequestBodyObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PaymanTransactionsReportStatisticsManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public Response getStatistics(PaymanTransactionsReportStatisticsInputDTO inputDto) throws IOException {
        RequestBody body = createRequestBody();
        Request request = getRequest(body, Urls.REPORTS_TRANSACTIONS_STATISTICS.getValue(), getHeaders());
        Response response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() {

        PaymanTransactionsReportStatisticsRequestBodyObject requestBodyObject = getRequestBodyObject();

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanTransactionsReportStatisticsRequestBodyObject getRequestBodyObject() {
        PaymanTransactionsReportStatisticsRequestBodyObject requestBodyObject = new PaymanTransactionsReportStatisticsRequestBodyObject();
        requestBodyObject.setStartDate("...");
        requestBodyObject.setEndDate("...");
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
