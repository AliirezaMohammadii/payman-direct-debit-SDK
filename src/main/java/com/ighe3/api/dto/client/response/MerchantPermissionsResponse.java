package com.ighe3.api.dto.client.response;

import com.ighe3.api.dto.MerchantPermissionDetails;
import com.ighe3.api.dto.provider.response.PaymanMerchantPermissionsResponse;
import lombok.Data;

import java.util.List;

@Data
public class MerchantPermissionsResponse {

    private List<MerchantPermissionDetails> merchantPermissions;

    public MerchantPermissionsResponse(PaymanMerchantPermissionsResponse paymanResponse) {
        this.merchantPermissions = paymanResponse.getMerchantPermissions();
    }
}
