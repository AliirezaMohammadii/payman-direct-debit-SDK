package com.payman.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.Contract;
import com.payman.api.dto.client.OverDraft;
import com.payman.api.dto.provider.response.PaymanTraceCreationResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TraceCreationResponse {

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("payman_id")
    private String paymanId;

    @JsonProperty("payman_code")
    private String paymanCode;

    private String status;

    @JsonProperty("internal_status")
    private String internalStatus;

    @JsonProperty("permission_ids")
    private List<Integer> permissionIds;

    private Contract contract;

    @JsonProperty("over_draft")
    private OverDraft overDraft;

    public TraceCreationResponse(PaymanTraceCreationResponse paymanResponse) {
        this.bankCode = paymanResponse.getBankCode();
        this.userId = paymanResponse.getUserId();
        this.paymanId = paymanResponse.getPaymanId();
        this.paymanCode = paymanResponse.getPaymanCode();
        this.status = paymanResponse.getStatus();
        this.internalStatus = paymanResponse.getInternalStatus();
        this.permissionIds = paymanResponse.getPermissionIds();
        this.contract = new Contract(paymanResponse.getContract());
        this.overDraft = new OverDraft(paymanResponse.getOverDraft());
    }
}
