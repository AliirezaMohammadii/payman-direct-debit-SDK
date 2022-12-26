package com.ighe3.api.model.payman;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PaymanObject {

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("permission_ids")
    private List<Integer> permissionIds;

    @JsonProperty("contract")
    private ContractObject contractObject;
}
