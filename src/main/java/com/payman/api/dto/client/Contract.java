package com.payman.api.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.PaymanContract;
import com.payman.api.utils.DateUtils;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Contract {

    @JsonProperty("start_date")
    private Long startDateEpochMillis;

    @JsonProperty("expiration_date")
    private Long expirationDateEpochMillis;

    @JsonProperty("max_daily_transaction_count")
    private Integer maxDailyTransactionCount;

    @JsonProperty("max_monthly_transaction_count")
    private Integer maxMonthlyTransactionCount;

    @JsonProperty("max_transaction_amount")
    private Double maxTransactionAmount;

    @JsonProperty("daily_max_transaction_amount")
    private Double dailyMaxTransactionAmount;

    public Contract(PaymanContract paymanContract) {
        this.startDateEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanContract.getStartDate());
        this.expirationDateEpochMillis = DateUtils.paymanDateTimeFormatToEpochMillis(paymanContract.getExpirationDate());
        this.maxDailyTransactionCount = paymanContract.getMaxDailyTransactionCount();
        this.maxMonthlyTransactionCount = paymanContract.getMaxMonthlyTransactionCount();
        this.maxTransactionAmount = paymanContract.getMaxTransactionAmount();
        this.dailyMaxTransactionAmount = paymanContract.getDailyMaxTransactionAmount();
    }
}
