package com.ighe3.api.dto.provider.request;

import com.ighe3.api.dto.TransactionsRequestFilter;
import lombok.Data;

@Data
public class PaymanTransactionsRequest {

    private Integer offset;
    private Integer length;
    private TransactionsRequestFilter filter;
}
