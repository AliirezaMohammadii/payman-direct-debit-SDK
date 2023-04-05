package com.ighe3.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.Contract;
import com.ighe3.api.dto.provider.response.PaymanTraceCreationResponse;
import lombok.Data;

import java.util.List;

@Data
public class TraceCreationResponse {

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

    public TraceCreationResponse(PaymanTraceCreationResponse paymanResponse) {
        this.bankCode = paymanResponse.getBankCode();
        this.userId = paymanResponse.getUserId();
        this.paymanId = paymanResponse.getPaymanId();
        this.status = paymanResponse.getStatus();
        this.internalStatus = paymanResponse.getInternalStatus();
        this.permissionIds = paymanResponse.getPermissionIds();
        this.contract = paymanResponse.getContract();
    }
}
