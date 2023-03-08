package com.ighe3.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PaymanUpdateRequest {
    @JsonProperty("payman_id")
    private String paymanId;
    @JsonProperty("expiration_date")
    private Date expirationDate;
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
}