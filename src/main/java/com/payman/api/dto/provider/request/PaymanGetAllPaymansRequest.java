package com.payman.api.dto.provider.request;

import com.payman.api.dto.client.GetAllPaymansRequestFilter;
import com.payman.api.dto.client.request.GetAllPaymansRequest;
import com.payman.api.dto.provider.PaymanGetAllPaymansRequestFilter;

public class PaymanGetAllPaymansRequest {

    private Integer offset;
    private Integer length;
    private PaymanGetAllPaymansRequestFilter filter;

    public PaymanGetAllPaymansRequest(GetAllPaymansRequest request) {
        this.offset = request.getOffset();
        this.length = request.getLength();
        this.filter = new PaymanGetAllPaymansRequestFilter(request.getFilter());
    }
}
