package com.ighe3.api.controller.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateInputDto {
    @JsonProperty("bank_code")
    private String bankCode;

    // TODO: remove
    @JsonProperty("user_id")
    private String userId;

    // TODO: remove
    @JsonProperty("permission_ids")
    private List<Integer> permissionIds;

    @JsonProperty("start_date")
    private Date startDate;
    @JsonProperty("expiration_date")
    private Date expirationDate;
    @JsonProperty("max_daily_transaction_count")
    private Integer maxDailyTransactionCount;
    @JsonProperty("max_monthly_transaction_count")
    private Integer maxMonthlyTransactionCount;
    @JsonProperty("max_transaction_amount")
    private Double maxTransactionAmount;
    private String currency;

    // TODO: remove
    private String redirectUrl;

    @JsonProperty("trace_id")
    private String traceId;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("national_code")
    private String nationalCode;
}
