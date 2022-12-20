package com.ighe3.api.management;

import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.SecurityUtils;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PaymanGetAccessTokenManager extends PaymanBaseManager {

    public Response getAccessToken() {
        FormBody body = getFormBody();
        Request request = getRequest(body, Urls.GET_TOKEN.getValue(), getHeaders());
        Response response = sendRequest(request);
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
    protected Headers getHeaders() {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        return generalHeaders;
    }
}
