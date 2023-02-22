package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.model.response.PaymanGetAccessTokenResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.SecurityUtils;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenService extends BaseService {
    public Object getAccessToken() throws Exception {
        ResponseObject paymanResponse = getResponseObject();
        PaymanGetAccessTokenResponse paymanResponseBody
                = (PaymanGetAccessTokenResponse) convertJsonToJavaObject(paymanResponse.getBody());
        return null;
    }

    private ResponseObject getResponseObject() throws Exception {
        FormBody requestBody = getFormBody();
        Request request = createRequest(requestBody, Urls.ACCESS_TOKEN.getValue(), createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
    }

    private FormBody getFormBody() {
        FormBody requestBody = new FormBody.Builder()
                .addEncoded("client_id", SecurityUtils.APP_KEY)
                .addEncoded("client_secret", SecurityUtils.CLIENT_SECRET)
                .addEncoded("grant_type", "client_credentials")
                .build();
        return requestBody;
    }

    @Override
    protected Headers createHeaders() {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        return generalHeaders;
    }

    @Override
    protected Object convertJsonToJavaObject(String value) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            PaymanGetAccessTokenResponse response = mapper.readValue(value, PaymanGetAccessTokenResponse.class);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
