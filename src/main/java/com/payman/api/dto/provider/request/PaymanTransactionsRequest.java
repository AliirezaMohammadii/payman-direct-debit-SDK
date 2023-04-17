package com.payman.api.dto.provider.request;

import com.payman.api.dto.client.TransactionsRequestFilter;
import com.payman.api.dto.client.request.TransactionsRequest;
import com.payman.api.dto.provider.PaymanTransactionsRequestFilter;

public class PaymanTransactionsRequest {

    private Integer offset;
    private Integer length;
    private PaymanTransactionsRequestFilter filter;

    public PaymanTransactionsRequest(TransactionsRequest request) {
        this.offset = request.getOffset();
        this.length = request.getLength();
        this.filter = new PaymanTransactionsRequestFilter(request.getFilter());
    }
}
