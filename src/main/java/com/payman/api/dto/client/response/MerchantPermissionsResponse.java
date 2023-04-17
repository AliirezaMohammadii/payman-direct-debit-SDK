package com.payman.api.dto.client.response;

import com.payman.api.dto.client.GetAllPaymansResponseResult;
import com.payman.api.dto.client.MerchantPermissionDetails;
import com.payman.api.dto.provider.response.PaymanMerchantPermissionsResponse;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class MerchantPermissionsResponse {

    private List<MerchantPermissionDetails> merchantPermissions;

    public MerchantPermissionsResponse(PaymanMerchantPermissionsResponse paymanResponse) {
        this.merchantPermissions = Optional.ofNullable(paymanResponse.getMerchantPermissions()).orElse(Collections.emptyList())
                .stream().map(MerchantPermissionDetails::new).collect(Collectors.toList());
    }
}
