package com.ighe3.api.dto.client.request;

import com.ighe3.api.dto.ListRequestFilter;
import lombok.Data;

@Data
public class ListRequest {

    private Integer offset;

    private Integer length;

    private ListRequestFilter filter;
}
