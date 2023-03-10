package com.ighe3.api.service.payman;

import com.ighe3.api.dto.MerchantPermissionDetails;
import com.ighe3.api.model.enums.MerchantPermissions;
import com.ighe3.api.dto.provider.response.PaymanMerchantPermissionsResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.Response;
import com.ighe3.api.utils.ExceptionTranslator;
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

    public MerchantPermissionsService(ExceptionTranslator exceptionTranslator, AccessTokenService accessTokenService, Urls urls) {
        super(exceptionTranslator);
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public List<Integer> getPermissionIds() throws RuntimeException {

        if (Boolean.parseBoolean(appHasOnlyNormalPayPermission))
            return Collections.singletonList(MerchantPermissions.NORMAL_PAY.label);

        Response paymanResponse = getBaseResponse();
        PaymanMerchantPermissionsResponse paymanResponseBody
                = (PaymanMerchantPermissionsResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanMerchantPermissionsResponse.class);
        return paymanResponseBody.getPermissionIdsDetail().stream().map(MerchantPermissionDetails::getId).collect(Collectors.toList());
    }

    private Response getBaseResponse() throws RuntimeException {
        Request request = createRequest(urls.getMerchantPermissionsUrl(), createHeaders());
        Response response = sendRequest(request, MerchantPermissionsService.class);
        return response;
    }

    @Override
    protected Headers createHeaders() throws RuntimeException {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .build();
        return headers;
    }
}
