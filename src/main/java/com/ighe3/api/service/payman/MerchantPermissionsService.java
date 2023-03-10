package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.model.PermissionIdDetail;
import com.ighe3.api.model.enums.Permission;
import com.ighe3.api.model.response.PaymanMerchantPermissionsResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.BaseResponse;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchantPermissionsService extends BaseService {

    @Value("${only.normal.pay}")
    private String appHasOnlyNormalPayPermission;

    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public MerchantPermissionsService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public List<Integer> getPermissionIds() throws Exception {

        try {
            if (Boolean.parseBoolean(appHasOnlyNormalPayPermission))
                return Collections.singletonList(Permission.NORMAL_PAY.label);
            // TODO: change to parse exception and test
        } catch (Exception e) {}

        BaseResponse paymanResponse = getBaseResponse();
        PaymanMerchantPermissionsResponse paymanResponseBody
                = (PaymanMerchantPermissionsResponse) convertJsonToJavaObject(paymanResponse.getBody());
        return paymanResponseBody.getPermissionIdsDetail().stream().map(PermissionIdDetail::getId).collect(Collectors.toList());
    }

    private BaseResponse getBaseResponse() throws Exception {
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
        ObjectMapper mapper = new ObjectMapper();
        try {
            PaymanMerchantPermissionsResponse response = mapper.readValue(value, PaymanMerchantPermissionsResponse.class);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
