package com.ighe3.api.dto.provider.request;

import com.ighe3.api.dto.ListRequestFilter;
import com.ighe3.api.dto.client.request.ListRequest;
import lombok.Data;

public class PaymanListRequest {

    private Integer offset;
    private Integer length;
    private ListRequestFilter filter;

    public PaymanListRequest(ListRequest request) {
        this.offset = request.getOffset();
        this.length = request.getLength();
        this.filter = request.getFilter();
    }
}
