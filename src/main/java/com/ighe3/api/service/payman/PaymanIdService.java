package com.ighe3.api.service.payman;

import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymanIdService extends BaseService {
    private final AccessTokenService accessTokenService;

    public PaymanIdService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public Object getPaymanId(String paymanCode) throws Exception {
        ResponseObject response = getResponseObject(paymanCode);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        String paymanId = (String) body.get("payman_id");
        return paymanId;
    }

    private ResponseObject getResponseObject(String paymanCode) throws Exception {
        String url = Urls.PAYMAN_ID.getValue() + "?payman_code" + "=" + paymanCode;
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
