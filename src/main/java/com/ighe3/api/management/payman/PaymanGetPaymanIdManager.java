package com.ighe3.api.management.payman;

import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class PaymanGetPaymanIdManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public ResponseObject getPaymanId(String paymanCode) throws Exception {
        Request request = createRequest(Urls.GET_PAYMAN_ID.getValue()
                        + "?payman_code" + "=" + paymanCode,
                createHeaders());
        ResponseObject response = sendRequest(request);
        printResponse(response);
        return response;
    }

    @Override
    protected Headers createHeaders() throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + paymanService.getAccessToken())
                .build();
        return headers;
    }
}
