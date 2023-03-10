package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateRequest {

    @JsonProperty("bank_code")
    private String bankCode;

    // TODO: remove
//    @JsonProperty("user_id")
//    private String userId;

    // TODO: remove
//    @JsonProperty("permission_ids")
//    private List<Integer> permissionIds;

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

    // TODO: remove
//    private String redirectUrl;

    // TODO: to remove
//    @JsonProperty("trace_id")
//    private String traceId;

    // TODO: in fact, mobileNumber and nationalCode must be handles by fetching from database.
    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("national_code")
    private String nationalCode;
}
