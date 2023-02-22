package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.model.response.PaymanGetPaymanIdResponse;
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
        Map<String, Object> body = convertJsonToJavaObject(response.getBody());
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

    @Override
    protected Object convertJsonToJavaObject(String value) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            PaymanGetPaymanIdResponse response = mapper.readValue(value, PaymanGetPaymanIdResponse.class);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
