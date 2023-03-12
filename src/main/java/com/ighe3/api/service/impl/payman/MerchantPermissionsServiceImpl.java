package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.MerchantPermissionDetails;
import com.ighe3.api.dto.enums.MerchantPermission;
import com.ighe3.api.dto.provider.response.PaymanMerchantPermissionsResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.service.payman.MerchantPermissionsService;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchantPermissionsServiceImpl implements MerchantPermissionsService {

    @Value("${only.normal.pay}")
    private String appHasOnlyNormalPayPermission;

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public MerchantPermissionsServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public List<Integer> getPermissionIds() throws BaseException {

        if (Boolean.parseBoolean(appHasOnlyNormalPayPermission))
            return Collections.singletonList(MerchantPermission.NORMAL_PAY.label);

        Request request = httpService.createRequest(urls.getMerchantPermissionsUrl(), httpService.createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = httpService.sendRequest(request, MerchantPermissionsServiceImpl.class);
        PaymanMerchantPermissionsResponse paymanResponseBody
                = (PaymanMerchantPermissionsResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanMerchantPermissionsResponse.class);
        return paymanResponseBody.getPermissionIdsDetail().stream().map(MerchantPermissionDetails::getId).collect(Collectors.toList());
    }
}
