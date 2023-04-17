package com.payman.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.request.UpdateRequest;
import com.payman.api.utils.DateUtils;
import lombok.Data;

@Data
public class PaymanUpdateRequest {

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

    public PaymanUpdateRequest(UpdateRequest request) {
        this.paymanId = request.getRedirectUrl();
        this.expirationDate = DateUtils.epochMillisToPaymanDateTimeFormat(request.getExpirationDateEpochMillis());
        this.maxDailyTransactionCount = request.getMaxDailyTransactionCount();
        this.maxMonthlyTransactionCount = request.getMaxMonthlyTransactionCount();
        this.maxTransactionAmount = request.getMaxTransactionAmount();
        this.dailyMaxTransactionAmount = request.getDailyMaxTransactionAmount();
        this.redirectUrl = request.getRedirectUrl();
    }
}
