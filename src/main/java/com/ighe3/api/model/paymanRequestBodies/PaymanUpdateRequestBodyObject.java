package com.ighe3.api.model.paymanRequestBodies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.model.PaymanObject;
import lombok.Data;

@Data
public class PaymanUpdateRequestBodyObject {

    @JsonProperty("payman_id")
    private String paymanId;

    @JsonProperty("expiration_date")
    private String expirationDate;

    @JsonProperty("max_daily_transaction_count")
    private String maxDailyTransactionCount;

    @JsonProperty("max_monthly_transaction_count")
    private String maxMonthlyTransactionCount;

    @JsonProperty("max_transaction_amount")
    private String maxTransactionAmount;

    @JsonProperty("redirect_url")
    private String redirectUrl;
}
