package com.ighe3.api.dto.provider.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.MerchantPermissionDetails;
import com.ighe3.api.dto.Contract;
import lombok.Data;

import java.util.List;

@Data
public class PaymanTraceCreateResponse {

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("payman_id")
    private String paymanId;

    private String status;

    @JsonProperty("internal_status")
    private String internalStatus;

    @JsonProperty("permission_ids")
    private List<Integer> permissionIds;

    private Contract contract;
}
