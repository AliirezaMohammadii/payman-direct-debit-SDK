package com.ighe3.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReportResponse {

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
}
