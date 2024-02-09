package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PayRequest extends BaseRequest {

    @JsonProperty("payman_id")
    private String paymanId;

    private Double amount;

    private String description;

    @JsonProperty("client_transaction_date")
    private String clientTransactionDate;

    @JsonProperty("trace_id")
    private String traceId;

    @JsonProperty("pay_id")
    private String payId;

    @JsonProperty("commission_amount")
    private String commissionAmount;
}
