package com.ighe3.api.dto.provider.request;

import com.ighe3.api.dto.TransactionsRequestFilter;
import lombok.Data;

@Data
public class PaymanTransactionsRequest {

    private TransactionsRequestFilter filter;

    private Integer length;

    private Integer offset;
}
