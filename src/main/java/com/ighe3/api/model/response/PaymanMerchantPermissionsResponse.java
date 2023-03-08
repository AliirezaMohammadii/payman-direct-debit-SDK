package com.ighe3.api.model.response;

import com.ighe3.api.model.PermissionIdDetail;
import lombok.Data;

import java.util.List;

@Data
public class PaymanMerchantPermissionsResponse {
    private List<PermissionIdDetail> permissionIdsDetail;
}
