package com.ighe3.api.model.request;

import com.ighe3.api.model.PaymanTransactionsRequestFilter;
import lombok.Data;

@Data
public class PaymanTransactionsRequest {
    private PaymanTransactionsRequestFilter filter;
    private Integer length;
    private Integer offset;
}
