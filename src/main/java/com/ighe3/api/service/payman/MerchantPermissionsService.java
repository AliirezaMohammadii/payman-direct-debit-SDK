package com.ighe3.api.service.payman;

import com.ighe3.api.service.BaseService;
import com.ighe3.api.dto.BaseResponse;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class MerchantPermissionsService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public MerchantPermissionsService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public Object getPermissions() throws Exception {
        BaseResponse response = getResponseObject();
//        List<Map<String, Object>> body = convertJsonToJavaObject(response.getBody());
        return null;
    }

    private BaseResponse getResponseObject() throws Exception {
        Request request = createRequest(urls.getMerchantPermissionsUrl(), createHeaders());
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

    // TODO
    @Override
    protected Object convertJsonToJavaObject(String value) {
        return null;
    }
}
