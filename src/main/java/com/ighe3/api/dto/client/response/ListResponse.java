package com.ighe3.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.ListResponseResult;
import com.ighe3.api.dto.provider.response.PaymanListResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListResponse {

    private List<ListResponseResult> results;

    @JsonProperty("total_records")
    private Integer totalRecords;

    public ListResponse(PaymanListResponse paymanResponse) {
        this.results = paymanResponse.getResults();
        this.totalRecords = paymanResponse.getTotalRecords();
    }
}
