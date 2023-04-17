package com.payman.api.dto.client.request;

import com.payman.api.dto.client.GetAllPaymansRequestFilter;
import lombok.Data;

@Data
public class GetAllPaymansRequest {

    private Integer offset;
    private Integer length;
    private GetAllPaymansRequestFilter filter;
}
