package com.payman.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetAllPaymansResponseResult {

    @JsonProperty("payman_id")
    private String paymanId;

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("user_id")
    private String userId;

    private String status;

    @JsonProperty("creditor_title")
    private String creditorTitle;

    @JsonProperty("trace_id")
    private String traceId;

    @JsonProperty("creditor_id")
    private String creditorId;

    @JsonProperty("permission_ids")
    private List<Integer> permissionIds;

    @JsonProperty("registration_date")
    private String registrationDate;

    private Contract contract;
}
