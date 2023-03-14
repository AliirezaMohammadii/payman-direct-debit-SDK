package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.TransactionsRequestFilter;
import lombok.Data;

@Data
public class TransactionsRequest {

    private Integer offset;
    private Integer length;
    private TransactionsRequestFilter filter;

    @JsonProperty("source_info")
    private SourceInfo sourceInfo;
}
