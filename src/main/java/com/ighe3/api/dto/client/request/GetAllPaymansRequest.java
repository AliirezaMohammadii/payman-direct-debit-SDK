package com.ighe3.api.dto.client.request;

import com.ighe3.api.dto.GetAllPaymansRequestFilter;
import lombok.Data;

@Data
public class GetAllPaymansRequest {

    private Integer offset;
    private Integer length;
    private GetAllPaymansRequestFilter filter;
}
