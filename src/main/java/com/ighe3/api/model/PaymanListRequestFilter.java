package com.ighe3.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PaymanListRequestFilter {

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("expiration_date_from")
    private Date expirationDateFrom;

    @JsonProperty("expiration_date_to")
    private Date expirationDateTo;

    @JsonProperty("max_daily_transactions_count_from")
    private Integer maxDailyTransactionsCountFrom;

    @JsonProperty("max_daily_transactions_count_to")
    private Integer maxDailyTransactionsCountTo;

    @JsonProperty("max_monthly_transactions_count_from")
    private Integer maxMonthlyTransactionsCountFrom;

    @JsonProperty("max_monthly_transactions_count_to")
    private Integer maxMonthlyTransactionsCountTo;

    @JsonProperty("start_date_from")
    private Date startDateFrom;

    @JsonProperty("start_date_to")
    private Date startDateTo;

    private List<String> statuses;

    @JsonProperty("transaction_max_amount_from")
    private Double transactionMaxAmountFrom;

    @JsonProperty("transaction_max_amount_to")
    private Double transactionMaxAmountTo;

    @JsonProperty("payman_id")
    private String paymanId;

    @JsonProperty("user_ids")
    private List<String> userIds;

    @JsonProperty("permission_ids")
    private List<Integer> permissionIds;
}
