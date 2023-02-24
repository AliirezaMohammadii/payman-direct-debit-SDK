package com.ighe3.api.service.payman;

import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseModel;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class MerchantPermissionsService extends BaseService {
    private final AccessTokenService accessTokenService;

    public MerchantPermissionsService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public Object getPermissions() throws Exception {
        ResponseModel response = getResponseObject();
//        List<Map<String, Object>> body = convertJsonToJavaObject(response.getBody());
        return null;
    }

    private ResponseModel getResponseObject() throws Exception {
        Request request = createRequest(Urls.MERCHANT_PERMISSIONS.getValue(), createHeaders());
        ResponseModel response = sendRequest(request);
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
