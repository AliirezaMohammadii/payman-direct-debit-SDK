package com.payman.api.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.Contract;
import com.payman.api.dto.provider.PaymanGetAllPaymansRequestFilter;
import com.payman.api.dto.provider.PaymanGetAllPaymansResponseResult;
import com.payman.api.utils.DateUtils;
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
    private Long registrationDateEpochMillis;

    private Contract contract;

    public GetAllPaymansResponseResult(PaymanGetAllPaymansResponseResult paymanGetAllPaymansResponseResult) {
        this.paymanId = paymanGetAllPaymansResponseResult.getPaymanId();
        this.bankCode = paymanGetAllPaymansResponseResult.getBankCode();
        this.userId = paymanGetAllPaymansResponseResult.getUserId();
        this.status = paymanGetAllPaymansResponseResult.getStatus();
        this.creditorTitle = paymanGetAllPaymansResponseResult.getCreditorTitle();
        this.traceId = paymanGetAllPaymansResponseResult.getTraceId();
        this.creditorId = paymanGetAllPaymansResponseResult.getCreditorId();
        this.permissionIds = paymanGetAllPaymansResponseResult.getPermissionIds();
        this.registrationDateEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanGetAllPaymansResponseResult.getRegistrationDate());
        this.contract = new Contract(paymanGetAllPaymansResponseResult.getContract());
    }
}
