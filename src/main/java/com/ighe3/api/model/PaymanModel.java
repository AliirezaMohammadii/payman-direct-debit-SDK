package com.ighe3.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PaymanModel {
    @JsonProperty("payman_id")
    private String bankCode;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("permission_ids")
    private List<Integer> permissionIds;
    @JsonProperty("contract")
    private PaymanContract contract;
}
