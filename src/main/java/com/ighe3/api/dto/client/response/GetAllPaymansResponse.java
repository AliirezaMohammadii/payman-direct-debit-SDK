package com.ighe3.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.GetAllPaymansResponseResult;
import com.ighe3.api.dto.provider.response.PaymanGetAllPaymansResponse;
import lombok.Data;

import java.util.List;

@Data
public class GetAllPaymansResponse {

    private List<GetAllPaymansResponseResult> results;

    @JsonProperty("total_records")
    private Integer totalRecords;

    public GetAllPaymansResponse(PaymanGetAllPaymansResponse paymanResponse) {
        this.results = paymanResponse.getResults();
        this.totalRecords = paymanResponse.getTotalRecords();
    }
}
