package com.ighe3.api.service.payman;

import com.ighe3.api.dal.dto.input.TransactionsReportStatisticsInputDTO;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.request.PaymanTransactionsReportStatisticsRequestBodyObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransactionsReportStatisticsService extends BaseService {
    private final AccessTokenService accessTokenService;

    public TransactionsReportStatisticsService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public Object getReportStatistics(TransactionsReportStatisticsInputDTO inputDto) throws Exception {
        ResponseObject response = getResponseObject();
        Map<String, Object> body = convertJsonToJavaObject(response.getBody());
        return null;
    }

    private ResponseObject getResponseObject() throws Exception {
        RequestBody requestBody = createRequestBody();
        Request request = createRequest(requestBody, Urls.TRANSACTIONS_REPORT_STATISTICS.getValue(), createHeaders());
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
}
