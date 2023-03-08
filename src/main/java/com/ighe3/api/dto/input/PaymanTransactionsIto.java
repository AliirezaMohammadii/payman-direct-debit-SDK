package com.ighe3.api.dto.input;

import com.ighe3.api.model.PaymanTransactionsRequestFilter;
import lombok.Data;

@Data
public class PaymanTransactionsIto {
    private Integer offset;
    private Integer length;
    private PaymanTransactionsRequestFilter filter;
}
