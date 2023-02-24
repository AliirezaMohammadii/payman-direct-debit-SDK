package com.ighe3.api.model.request;

import com.ighe3.api.model.PaymanRequestFilter;
import lombok.Data;

@Data
public class PaymanTransactionsRequestBodyObject {

    private PaymanRequestFilter filter;
    private String length;
    private String offset;
}
