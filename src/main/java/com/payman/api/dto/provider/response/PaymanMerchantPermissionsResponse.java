package com.payman.api.dto.provider.response;

import com.payman.api.dto.MerchantPermissionDetails;
import lombok.Data;

import java.util.List;

@Data
public class PaymanMerchantPermissionsResponse {

    private List<MerchantPermissionDetails> merchantPermissions;
}
