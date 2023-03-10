package com.ighe3.api.dto.provider.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.ListResponseResult;
import lombok.Data;

import java.util.List;

@Data
public class PaymanListResponse {

    private List<ListResponseResult> results;

    @JsonProperty("total_records")
    private Integer totalRecords;
}
