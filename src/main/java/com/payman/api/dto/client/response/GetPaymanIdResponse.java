package com.payman.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payman.api.dto.provider.response.PaymanGetPaymanIdResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetPaymanIdResponse {

    @JsonProperty("payman_id")
    private String paymanId;

    private String status;

    @JsonProperty("deposit_number")
    private String depositNumber;

    public GetPaymanIdResponse(PaymanGetPaymanIdResponse paymanResponse) {
        this.paymanId = paymanResponse.getPaymanId();
        this.status = paymanResponse.getStatus();
        this.depositNumber = paymanResponse.getDepositNumber();
    }
}
