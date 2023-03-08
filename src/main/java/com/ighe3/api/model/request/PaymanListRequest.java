package com.ighe3.api.model.request;

import com.ighe3.api.model.PaymanListRequestFilter;
import com.ighe3.api.model.PaymanTransactionsRequestFilter;
import lombok.Data;

@Data
public class PaymanListRequest {
    private PaymanListRequestFilter filter;
    private Integer length;
    private Integer offset;
}
