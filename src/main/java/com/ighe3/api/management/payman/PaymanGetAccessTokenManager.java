package com.ighe3.api.management.payman;

import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.SecurityUtils;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class PaymanGetAccessTokenManager extends PaymanBaseManager {

    public ResponseObject getAccessToken() throws Exception {
        FormBody body = getFormBody();
        Request request = createRequest(body, Urls.GET_TOKEN.getValue(), createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
    }

    private FormBody getFormBody() {
        FormBody body = new FormBody.Builder()
                .addEncoded("client_id", SecurityUtils.APP_KEY)
                .addEncoded("client_secret", SecurityUtils.CLIENT_SECRET)
                .addEncoded("grant_type", "client_credentials")
                .build();
        return body;
    }

    @Override
    protected Headers createHeaders() {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        return generalHeaders;
    }
}
