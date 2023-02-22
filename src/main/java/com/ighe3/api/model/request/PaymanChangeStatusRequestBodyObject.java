package com.ighe3.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanChangeStatusRequestBodyObject {

    @JsonProperty("new_status")
    private String newStatus;

    @JsonProperty("payman_id")
    private String paymanId;
}
