package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.model.response.PaymanReportResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.dto.BaseResponse;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.Headers;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class ReportService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public ReportService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public Object getReport(String paymanId) throws Exception {
        BaseResponse paymanResponse = getResponseObject(paymanId);
        PaymanReportResponse paymanResponseBody
                = (PaymanReportResponse) convertJsonToJavaObject(paymanResponse.getBody());
        return null;
    }

    private BaseResponse getResponseObject(String paymanId) throws Exception {
        String url = urls.getReportUrl() + "/" + paymanId;
        Request request = createRequest(url, createHeaders());
        BaseResponse response = sendRequest(request);
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
            PaymanReportResponse response = mapper.readValue(value, PaymanReportResponse.class);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
