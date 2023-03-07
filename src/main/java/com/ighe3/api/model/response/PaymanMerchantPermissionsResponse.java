package com.ighe3.api.model.response;

import lombok.Data;

import java.util.List;

@Data
public class PaymanMerchantPermissionsResponse {
    private List<Integer> permissionIds;
}
