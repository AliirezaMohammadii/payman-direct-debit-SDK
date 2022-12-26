package com.ighe3.api.model.payman;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanRequestFilterObject {

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("from_transaction_amount")
    private String fromTransactionAmount;

    @JsonProperty("to_transaction_amount")
    private String toTransactionAmount;

    // ...
}
