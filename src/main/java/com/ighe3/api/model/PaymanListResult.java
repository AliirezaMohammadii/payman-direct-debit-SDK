package com.ighe3.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PaymanListResult {
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
    private List<String> permissionIds;
    @JsonProperty("registration_date")
    private Date registrationDate;
    private PaymanContractModel contract;
}
