package com.payman.api.dto.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.Contract;
import com.payman.api.dto.client.PaymanDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PaymanPaymanDetails {

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("permission_ids")
    private List<Integer> permissionIds;

    @JsonProperty("contract")
    private PaymanContract contract;

    public PaymanPaymanDetails(PaymanDetails paymanDetails) {
        this.bankCode = paymanDetails.getBankCode();
        this.userId = paymanDetails.getUserId();
        this.permissionIds = paymanDetails.getPermissionIds();
        this.contract = new PaymanContract(paymanDetails.getContract());
    }
}
