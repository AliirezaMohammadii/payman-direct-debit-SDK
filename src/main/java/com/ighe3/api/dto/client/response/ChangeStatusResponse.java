package com.ighe3.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.provider.response.PaymanChangeStatusResponse;
import lombok.Data;

@Data
public class ChangeStatusResponse {

    @JsonProperty("payman_id")
    private String paymanId;

    private String status;

    public ChangeStatusResponse(PaymanChangeStatusResponse paymanResponse) {
        this.paymanId = paymanResponse.getPaymanId();
        this.status = paymanResponse.getStatus();
    }
}
