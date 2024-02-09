package com.ighe3.api.service.impl.payman;

import com.ighe3.api.config.GeneralPropertiesConfig;
import com.ighe3.api.config.UrlPropertiesConfig;
import com.ighe3.api.dto.client.request.MerchantPermissionsRequest;
import com.ighe3.api.dto.client.response.MerchantPermissionsResponse;
import com.ighe3.api.dto.enums.MerchantPermission;
import com.ighe3.api.dto.provider.response.PaymanMerchantPermissionsResponse;
import com.ighe3.api.mapper.ResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.service.payman.MerchantPermissionsService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class MerchantPermissionsServiceImpl implements MerchantPermissionsService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenServiceImpl accessTokenService;
    private final GeneralPropertiesConfig generalPropertiesConfig;

    public MerchantPermissionsServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenServiceImpl accessTokenService, GeneralPropertiesConfig generalPropertiesConfig) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
        this.generalPropertiesConfig = generalPropertiesConfig;
    }

    @Override
    public List<Integer> getPermissionIds() {
        if (Boolean.parseBoolean(generalPropertiesConfig.hasOnlyNormalPay()))
            return Collections.singletonList(MerchantPermission.NORMAL_PAY.code);
        return Arrays.asList(MerchantPermission.NORMAL_PAY.code, MerchantPermission.BILL_PAY.code);
    }

    @Override
    public MerchantPermissionsResponse getPermissionsDetail(MerchantPermissionsRequest request) throws IOException {
        Request paymanRequest = httpService.createRequest(
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getMerchantPermissions(),
                httpService.createHeaders(request.getSourceInfo(), accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, MerchantPermissionsServiceImpl.class);
        return (MerchantPermissionsResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanMerchantPermissionsResponse.class, MerchantPermissionsResponse.class);
    }
}
