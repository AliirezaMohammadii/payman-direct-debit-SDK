package com.payman.api.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.Contract;
import com.payman.api.dto.provider.PaymanContract;
import com.payman.api.dto.provider.PaymanPaymanDetails;
import com.payman.api.utils.DateUtils;
import lombok.Data;

import java.util.List;

@Data
public class PaymanDetails {

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("permission_ids")
    private List<Integer> permissionIds;

    @JsonProperty("contract")
    private Contract contract;

    public PaymanDetails(PaymanPaymanDetails paymanPaymanDetails) {
        this.bankCode = paymanPaymanDetails.getBankCode();
        this.userId = paymanPaymanDetails.getUserId();
        this.permissionIds = paymanPaymanDetails.getPermissionIds();
        this.contract = new Contract(paymanPaymanDetails.getContract());
    }
}
