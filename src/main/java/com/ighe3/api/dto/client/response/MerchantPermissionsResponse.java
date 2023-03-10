package com.ighe3.api.dto.client.response;

import com.ighe3.api.dto.MerchantPermissionDetails;
import lombok.Data;

import java.util.List;

@Data
public class MerchantPermissionsResponse {

    private List<MerchantPermissionDetails> paymanMerchantPermissions;
}
