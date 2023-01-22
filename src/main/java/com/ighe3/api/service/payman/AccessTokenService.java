package com.ighe3.api.service.payman;

import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.SecurityUtils;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccessTokenService extends BaseService {
    public Object getAccessToken() throws Exception {
        ResponseObject response = getResponseObject();
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        String accessToken = (String) body.get("access_token");
        return accessToken;
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
}
