package com.payman.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.client.GetAllPaymansResponseResult;
import com.payman.api.dto.provider.response.PaymanGetAllPaymansResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class GetAllPaymansResponse {

    private List<GetAllPaymansResponseResult> results;

    @JsonProperty("total_records")
    private Integer totalRecords;

    public GetAllPaymansResponse(PaymanGetAllPaymansResponse paymanResponse) {
        this.results = Optional.ofNullable(paymanResponse.getResults()).orElse(Collections.emptyList())
                .stream().map(GetAllPaymansResponseResult::new).collect(Collectors.toList());
        this.totalRecords = paymanResponse.getTotalRecords();
    }
}
