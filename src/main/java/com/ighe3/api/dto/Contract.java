package com.ighe3.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Contract {
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
}
