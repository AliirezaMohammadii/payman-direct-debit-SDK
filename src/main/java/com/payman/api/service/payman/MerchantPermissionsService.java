package com.payman.api.service.payman;

import com.payman.api.dto.client.response.MerchantPermissionsResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface MerchantPermissionsService {

    List<Integer> getPermissionIds() throws IOException;

    MerchantPermissionsResponse getPermissionsDetail(HttpServletRequest httpServletRequest) throws IOException;

    MerchantPermissionsResponse getPermissionsDetail() throws IOException;
}
