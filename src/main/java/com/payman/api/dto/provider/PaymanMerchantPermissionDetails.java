package com.payman.api.dto.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.MerchantPermissionDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymanMerchantPermissionDetails {

    private int id;
    private String name;
    @JsonProperty("display_name")
    private String displayName;

    public PaymanMerchantPermissionDetails(MerchantPermissionDetails merchantPermissionDetails) {
        this.id = merchantPermissionDetails.getId();
        this.name = merchantPermissionDetails.getName();
        this.displayName = merchantPermissionDetails.getDisplayName();
    }
}
