package com.payman.api.dto.provider.request;

import com.payman.api.dto.TransactionsRequestFilter;
import com.payman.api.dto.client.request.TransactionsRequest;

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
