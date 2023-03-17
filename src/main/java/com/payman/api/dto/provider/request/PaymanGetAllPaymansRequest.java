package com.payman.api.dto.provider.request;

import com.payman.api.dto.GetAllPaymansRequestFilter;
import com.payman.api.dto.client.request.GetAllPaymansRequest;

public class PaymanGetAllPaymansRequest {

    private Integer offset;
    private Integer length;
    private GetAllPaymansRequestFilter filter;

    public PaymanGetAllPaymansRequest(GetAllPaymansRequest request) {
        this.offset = request.getOffset();
        this.length = request.getLength();
        this.filter = request.getFilter();
    }
}
