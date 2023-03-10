package com.ighe3.api.dto.provider.response;

import com.ighe3.api.dto.MerchantPermissionDetails;
import lombok.Data;

import java.util.List;

@Data
public class PaymanMerchantPermissionsResponse {

    private List<MerchantPermissionDetails> permissionIdsDetail;
}
