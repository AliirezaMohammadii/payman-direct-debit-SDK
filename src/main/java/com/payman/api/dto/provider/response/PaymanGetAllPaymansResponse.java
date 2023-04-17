package com.payman.api.dto.provider.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.GetAllPaymansResponseResult;
import com.payman.api.dto.provider.PaymanGetAllPaymansResponseResult;
import lombok.Data;

import java.util.List;

@Data
public class PaymanGetAllPaymansResponse {

    private List<PaymanGetAllPaymansResponseResult> results;

    @JsonProperty("total_records")
    private Integer totalRecords;
}
