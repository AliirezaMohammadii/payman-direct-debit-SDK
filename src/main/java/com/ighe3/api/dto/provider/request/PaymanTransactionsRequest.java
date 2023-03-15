package com.ighe3.api.dto.provider.request;

import com.ighe3.api.dto.TransactionsRequestFilter;
import com.ighe3.api.dto.client.request.TransactionsRequest;
import lombok.Data;

public class PaymanTransactionsRequest {

    private Integer offset;
    private Integer length;
    private TransactionsRequestFilter filter;

    public PaymanTransactionsRequest(TransactionsRequest request) {
        this.offset = request.getOffset();
        this.length = request.getLength();
        this.filter = request.getFilter();
    }
}
