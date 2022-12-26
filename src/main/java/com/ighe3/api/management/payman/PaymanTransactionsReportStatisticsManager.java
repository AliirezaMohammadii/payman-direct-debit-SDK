package com.ighe3.api.management.payman;

import com.ighe3.api.dal.dto.payman.input.PaymanTransactionsReportStatisticsInputDTO;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.payman.requestBody.PaymanTransactionsReportStatisticsRequestBodyObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class PaymanTransactionsReportStatisticsManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public ResponseObject getStatistics(PaymanTransactionsReportStatisticsInputDTO inputDto) throws Exception {
        RequestBody body = createRequestBody();
        Request request = createRequest(body, Urls.REPORTS_TRANSACTIONS_STATISTICS.getValue(), createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() throws Exception {

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
