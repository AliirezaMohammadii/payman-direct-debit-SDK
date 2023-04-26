package com.payman.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.TransactionsRequestFilter;
import com.payman.api.dto.client.request.TransactionsRequest;
import com.payman.api.dto.provider.PaymanTransactionsRequestFilter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PaymanTransactionsRequest {

    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("length")
    private Integer length;

    @JsonProperty("filter")
    private PaymanTransactionsRequestFilter filter;

    public PaymanTransactionsRequest(TransactionsRequest request) {
        this.offset = request.getOffset();
        this.length = request.getLength();
        this.filter = new PaymanTransactionsRequestFilter(request.getFilter());
    }
}
