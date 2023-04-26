package com.payman.api.dto.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.Contract;
import com.payman.api.dto.client.GetAllPaymansResponseResult;
import com.payman.api.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PaymanGetAllPaymansResponseResult {

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

    private PaymanContract contract;

    public PaymanGetAllPaymansResponseResult(GetAllPaymansResponseResult getAllPaymansResponseResult) {
        this.paymanId = getAllPaymansResponseResult.getPaymanId();
        this.bankCode = getAllPaymansResponseResult.getBankCode();
        this.userId = getAllPaymansResponseResult.getUserId();
        this.status = getAllPaymansResponseResult.getStatus();
        this.creditorTitle = getAllPaymansResponseResult.getCreditorTitle();
        this.traceId = getAllPaymansResponseResult.getTraceId();
        this.creditorId = getAllPaymansResponseResult.getCreditorId();
        this.permissionIds = getAllPaymansResponseResult.getPermissionIds();
        this.registrationDate = DateUtils.epochMillisToPaymanDateTimeFormat(getAllPaymansResponseResult.getRegistrationDateEpochMillis());
        this.contract = new PaymanContract(getAllPaymansResponseResult.getContract());
    }
}
