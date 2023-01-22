package com.ighe3.api.service.payman;

import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.Headers;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReportService extends BaseService {
    private final AccessTokenService accessTokenService;

    public ReportService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public Object getReport(String paymanId) throws Exception {
        ResponseObject response = getResponseObject(paymanId);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return null;
    }

    private ResponseObject getResponseObject(String paymanId) throws Exception {
        String url = Urls.REPORT.getValue() + "/" + paymanId;
        Request request = createRequest(url, createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
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
