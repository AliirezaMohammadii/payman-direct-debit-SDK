package com.ighe3.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.provider.response.PaymanGetPaymanIdResponse;
import lombok.Data;

@Data
public class GetPaymanIdResponse {

    @JsonProperty("payman_id")
    private String paymanId;

    private String status;

    private String deposit;

    public GetPaymanIdResponse(PaymanGetPaymanIdResponse paymanResponse) {
        this.paymanId = paymanResponse.getPaymanId();
        this.status = paymanResponse.getStatus();
        this.deposit = paymanResponse.getDeposit();
    }
}
