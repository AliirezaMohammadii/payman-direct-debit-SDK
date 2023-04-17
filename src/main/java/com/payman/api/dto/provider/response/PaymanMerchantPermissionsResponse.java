package com.payman.api.dto.provider.response;

import com.payman.api.dto.client.MerchantPermissionDetails;
import com.payman.api.dto.provider.PaymanMerchantPermissionDetails;
import lombok.Data;

import java.util.List;

@Data
public class PaymanMerchantPermissionsResponse {

    private List<PaymanMerchantPermissionDetails> merchantPermissions;
}
