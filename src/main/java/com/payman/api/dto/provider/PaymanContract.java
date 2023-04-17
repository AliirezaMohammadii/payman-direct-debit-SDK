package com.payman.api.dto.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.Contract;
import com.payman.api.utils.DateUtils;
import lombok.Data;

@Data
public class PaymanContract {

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

    public PaymanContract(Contract contract) {
        this.startDate = DateUtils.epochMillisToPaymanDateTimeFormat(contract.getStartDateEpochMillis());
        this.expirationDate = DateUtils.epochMillisToPaymanDateTimeFormat(contract.getExpirationDateEpochMillis());
        this.maxDailyTransactionCount = contract.getMaxDailyTransactionCount();
        this.maxMonthlyTransactionCount = contract.getMaxMonthlyTransactionCount();
        this.maxTransactionAmount = contract.getMaxTransactionAmount();
        this.dailyMaxTransactionAmount = contract.getDailyMaxTransactionAmount();
    }
}
