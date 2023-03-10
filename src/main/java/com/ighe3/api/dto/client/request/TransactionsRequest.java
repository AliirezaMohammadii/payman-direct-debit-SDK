package com.ighe3.api.dto.client.request;

import com.ighe3.api.dto.TransactionsRequestFilter;
import lombok.Data;

@Data
public class TransactionsRequest {

    private Integer offset;

    private Integer length;

    private TransactionsRequestFilter filter;
}
