package com.ighe3.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PaymanObject {
    private String bankCode;
    private String userId;
    private List<Integer> permissionIds;
    @JsonProperty("contract")
    private ContractObject contractObject;
}
