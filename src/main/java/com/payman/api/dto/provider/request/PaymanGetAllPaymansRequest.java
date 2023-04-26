package com.payman.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.GetAllPaymansRequestFilter;
import com.payman.api.dto.client.request.GetAllPaymansRequest;
import com.payman.api.dto.provider.PaymanGetAllPaymansRequestFilter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PaymanGetAllPaymansRequest {

    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("length")
    private Integer length;

    @JsonProperty("filter")
    private PaymanGetAllPaymansRequestFilter filter;

    public PaymanGetAllPaymansRequest(GetAllPaymansRequest request) {
        this.offset = request.getOffset();
        this.length = request.getLength();
        this.filter = new PaymanGetAllPaymansRequestFilter(request.getFilter());
    }
}
