package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateRequest {

    @JsonProperty("payman_id")
    private String paymanId;

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

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("national_code")
    private String nationalCode;
}
