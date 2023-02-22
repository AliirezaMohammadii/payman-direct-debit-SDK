package com.ighe3.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.model.PaymanContractModel;
import lombok.Data;

import java.util.List;

@Data
public class PaymanTraceCreateResponse {
    @JsonProperty("bank_code")
    private String bankCode;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("payman_code")
    private String paymanId;
    private String status;
    @JsonProperty("internal_status")
    private String internalStatus;

    // TODO: must be list of enums
    @JsonProperty("permission_ids")
    private List<String> permissionIds;
    private PaymanContractModel contract;
}
