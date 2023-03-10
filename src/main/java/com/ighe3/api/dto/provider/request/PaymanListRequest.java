package com.ighe3.api.dto.provider.request;

import com.ighe3.api.dto.ListRequestFilter;
import lombok.Data;

@Data
public class PaymanListRequest {

    private ListRequestFilter filter;

    private Integer length;

    private Integer offset;
}
