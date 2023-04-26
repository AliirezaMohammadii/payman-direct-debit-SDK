package com.payman.api.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.PaymanMerchantPermissionDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MerchantPermissionDetails {

    private int id;

    private String name;

    @JsonProperty("display_name")
    private String displayName;

    public MerchantPermissionDetails(PaymanMerchantPermissionDetails paymanMerchantPermissionDetails) {
        this.id = paymanMerchantPermissionDetails.getId();
        this.name = paymanMerchantPermissionDetails.getName();
        this.displayName = paymanMerchantPermissionDetails.getDisplayName();
    }
}
