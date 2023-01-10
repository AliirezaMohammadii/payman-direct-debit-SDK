package com.ighe3.api.dal.dto.payman.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PaymanCreateInputDTO {

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("permission_ids")
    private List<Integer> permissionIds;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("expiration_date")
    private String expirationDate;

    @JsonProperty("max_daily_transaction_count")
    private Integer maxDailyTransactionCount;

    @JsonProperty("max_monthly_transaction_count")
    private Integer maxMonthlyTransactionCount;

    @JsonProperty("max_transaction_amount")
    private Double maxTransactionAmount;

    @JsonProperty("daily_max_transaction_amount")
    private Double dailyMaxTransactionAmount;

    @JsonProperty("redirect_url")
    private String redirectUrl;

    @JsonProperty("trace_id")
    private String traceId;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("national_code")
    private String nationalCode;
}
