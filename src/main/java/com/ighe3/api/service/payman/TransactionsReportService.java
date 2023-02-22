package com.ighe3.api.service.payman;

import com.ighe3.api.dal.dto.input.TransactionsReportInputDTO;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.request.PaymanTransactionsReportRequestBodyObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransactionsReportService extends BaseService {
    private final AccessTokenService accessTokenService;

    public TransactionsReportService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public Object getReport(TransactionsReportInputDTO inputDto) throws Exception {
        ResponseObject response = getResponseObject();
        Map<String, Object> body = convertJsonToJavaObject(response.getBody());
        return null;
    }

    private ResponseObject getResponseObject() throws Exception {
        RequestBody requestBody = createRequestBody();
        Request request = createRequest(requestBody, Urls.TRANSACTIONS_REPORT.getValue(), createHeaders());
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
        requestBodyObject.setStartDate(null);
        requestBodyObject.setEndDate(null);
        requestBodyObject.setBankCode(null);
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
