package com.ighe3.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.model.PaymanListResult;
import lombok.Data;

import java.util.List;

@Data
public class PaymanListResponse {
    private List<PaymanListResult> results;
    @JsonProperty("total_records")
    private Integer totalRecords;
}
