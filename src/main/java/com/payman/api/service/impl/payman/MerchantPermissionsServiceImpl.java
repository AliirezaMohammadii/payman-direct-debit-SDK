package com.payman.api.service.impl.payman;

import com.payman.api.config.GeneralPropertiesConfig;
import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.MerchantPermissionDetails;
import com.payman.api.dto.client.response.MerchantPermissionsResponse;
import com.payman.api.dto.enums.MerchantPermission;
import com.payman.api.dto.provider.response.CustomizedResponse;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.service.payman.AccessTokenService;
import com.payman.api.service.payman.MerchantPermissionsService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MerchantPermissionsServiceImpl implements MerchantPermissionsService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenService accessTokenService;
    private final GeneralPropertiesConfig generalPropertiesConfig;

    public MerchantPermissionsServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenService accessTokenService, GeneralPropertiesConfig generalPropertiesConfig) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
        this.generalPropertiesConfig = generalPropertiesConfig;
    }

    @Override
    public List<Integer> getPermissionIds() throws IOException {
        if (Boolean.parseBoolean(generalPropertiesConfig.hasOnlyDirectDebit()))
            return Collections.singletonList(MerchantPermission.DIRECT_DEBIT.code);
        return getPermissionsDetail(null).getPermissions().stream().map(MerchantPermissionDetails::getId).collect(Collectors.toList());
    }

    @Override
    public MerchantPermissionsResponse getPermissionsDetail(HttpServletRequest httpServletRequest) throws IOException {
        Headers headers = Objects.isNull(httpServletRequest) ?
                httpService.createInternalRequestHeaders(accessTokenService.getAccessToken()) :
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken());

        Request paymanRequest = httpService.createRequest(
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getMerchantPermissions(), headers);

        CustomizedResponse paymanCustomizedResponse = httpService.sendRequest(paymanRequest, MerchantPermissionsServiceImpl.class);
        return (MerchantPermissionsResponse) ResponseMapper
                .map(paymanCustomizedResponse.getBody(), List.class, MerchantPermissionsResponse.class);
    }
}
