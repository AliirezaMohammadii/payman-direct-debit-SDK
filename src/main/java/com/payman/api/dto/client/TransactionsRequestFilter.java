package com.payman.api.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.PaymanGetAllPaymansRequestFilter;
import com.payman.api.dto.provider.PaymanTransactionsRequestFilter;
import com.payman.api.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TransactionsRequestFilter {

    @JsonProperty("payman_ids")
    private List<String> paymanIds;

    @JsonProperty("user_ids")
    private List<String> userIds;

    @JsonProperty("trace_id")
    private String traceId;

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("from_transaction_amount")
    private Double fromTransactionAmount;

    @JsonProperty("to_transaction_amount")
    private Double toTransactionAmount;

    @JsonProperty("from_transaction_date")
    private Long fromTransactionDateEpochMillis;

    @JsonProperty("to_transaction_date")
    private Long toTransactionDateEpochMillis;

    @JsonProperty("note")
    private String note;

    @JsonProperty("source_bank_code")
    private String sourceBankCode;

    @JsonProperty("destination_bank_code")
    private String destinationBankCode;

    @JsonProperty("payman_statuses")
    private List<String> paymanStatuses;

    @JsonProperty("transaction_statuses")
    private List<String> transactionStatuses;
}
