package com.ighe3.api.model.payman.requestBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanChangeStatusRequestBodyObject {

    @JsonProperty("new_status")
    private String newStatus;

    @JsonProperty("payman_id")
    private String paymanId;
}
