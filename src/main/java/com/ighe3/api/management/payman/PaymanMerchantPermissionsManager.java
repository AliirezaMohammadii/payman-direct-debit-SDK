package com.ighe3.api.management.payman;

import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class PaymanMerchantPermissionsManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public ResponseObject getList() throws Exception {
        Request request = createRequest(Urls.MERCHANT_PERMISSIONS.getValue(), createHeaders());
        ResponseObject response = sendRequest(request);
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
