package com.ighe3.api.controller.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.model.PaymanListResult;
import lombok.Data;

import java.util.List;

@Data
public class PaymanListOto {
    private List<PaymanListResult> results;
    @JsonProperty("total_records")
    private Integer totalRecords;
}
