package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.MerchantPermissionsRequest;
import com.ighe3.api.dto.client.response.MerchantPermissionsResponse;
import com.ighe3.api.dto.provider.response.PaymanMerchantPermissionsResponse;

import java.io.IOException;
import java.util.List;

public interface MerchantPermissionsService {

    List<Integer> getPermissionIds();

    MerchantPermissionsResponse getPermissionsDetail(MerchantPermissionsRequest request) throws IOException;
}
