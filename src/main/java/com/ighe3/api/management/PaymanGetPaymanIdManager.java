package com.ighe3.api.management;

import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PaymanGetPaymanIdManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public Response getPaymanId(String paymanCode) throws IOException {
        Request request = getRequest(Urls.GET_PAYMAN_ID.getValue()
                        + "?payman_code" + "=" + paymanCode,
                getHeaders());
        Response response = sendRequest(request);
        printResponse(response);
        return response;
    }

    @Override
    protected Headers getHeaders() throws IOException {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + paymanService.getAccessToken())
                .build();
        return headers;
    }
}
