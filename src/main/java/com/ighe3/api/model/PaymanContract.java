package com.ighe3.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PaymanContract {
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
}
