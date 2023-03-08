package com.ighe3.api.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PaymanUpdateIto {

    // TODO: it's temporary. must be removed later.
    @JsonProperty("user_id")
    private String userId;

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

    // TODO: remove
//    private String redirectUrl;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("national_code")
    private String nationalCode;
}
