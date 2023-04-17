package com.payman.api.dto.client.request;

import com.payman.api.dto.client.TransactionsRequestFilter;
import lombok.Data;

@Data
public class TransactionsRequest {

    private Integer offset;
    private Integer length;
    private TransactionsRequestFilter filter;
}
