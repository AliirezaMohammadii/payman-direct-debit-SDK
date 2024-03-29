package com.ighe3.api.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.client.request.ChangeStatusRequest;
import lombok.Data;

public class PaymanChangeStatusRequest {

    @JsonProperty("payman_id")
    private String paymanId;

    @JsonProperty("new_status")
    private String newStatus;

    public PaymanChangeStatusRequest(ChangeStatusRequest request) {
        this.paymanId = request.getPaymanId();
        this.newStatus = request.getNewStatus();
    }
}
