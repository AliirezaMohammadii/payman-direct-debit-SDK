package com.payman.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.response.PaymanGetPaymanResponse;
import lombok.Data;

@Data
public class GetPaymanResponse {

    @JsonProperty("payman_id")
    private String paymanId;

    @JsonProperty("expiration_date")
    private String expirationDate;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("remaining_daily_transactions_count")
    private String remainingDailyTransactionsCount;

    @JsonProperty("remaining_monthly_transactions_count")
    private String remainingMonthlyTransactionsCount;

    @JsonProperty("end_of_current_month")
    private String endOfCurrentMonth;

    @JsonProperty("max_daily_transactions_count")
    private String maxDailyTransactionsCount;

    @JsonProperty("max_monthly_transactions_count")
    private String maxMonthlyTransactionsCount;

    @JsonProperty("remaining_days")
    private String remainingDays;

    @JsonProperty("remaining_daily_transactions_amount")
    private String remainingDailyTransactionsAmount;

    public GetPaymanResponse(PaymanGetPaymanResponse paymanResponse) {
        this.paymanId = paymanResponse.getPaymanId();
        this.expirationDate = paymanResponse.getExpirationDate();
        this.startDate = paymanResponse.getStartDate();
        this.remainingDailyTransactionsCount = paymanResponse.getRemainingDailyTransactionsCount();
        this.remainingMonthlyTransactionsCount = paymanResponse.getRemainingMonthlyTransactionsCount();
        this.endOfCurrentMonth = paymanResponse.getEndOfCurrentMonth();
        this.maxDailyTransactionsCount = paymanResponse.getMaxDailyTransactionsCount();
        this.maxMonthlyTransactionsCount = paymanResponse.getMaxMonthlyTransactionsCount();
        this.remainingDays = paymanResponse.getRemainingDays();
        this.remainingDailyTransactionsAmount = paymanResponse.getRemainingDailyTransactionsAmount();
    }
}
