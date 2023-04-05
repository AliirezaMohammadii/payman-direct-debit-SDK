package com.payman.api.dto.client.response;

import com.payman.api.dto.MerchantPermissionDetails;
import com.payman.api.dto.provider.response.PaymanMerchantPermissionsResponse;
import lombok.Data;

import java.util.List;

@Data
public class MerchantPermissionsResponse {

    private List<MerchantPermissionDetails> merchantPermissions;

    public MerchantPermissionsResponse(PaymanMerchantPermissionsResponse paymanResponse) {
        this.merchantPermissions = paymanResponse.getMerchantPermissions();
    }
}
