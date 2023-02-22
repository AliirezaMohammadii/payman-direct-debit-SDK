package com.ighe3.api.model.response;

import lombok.Data;

import java.util.List;

// TODO: response is a list of object and I don't know how to handle it exactly.
@Data
public class PaymanMerchantPermissionsResponse {
    private List<PaymanMerchantPermission> paymanMerchantPermissions;
}
