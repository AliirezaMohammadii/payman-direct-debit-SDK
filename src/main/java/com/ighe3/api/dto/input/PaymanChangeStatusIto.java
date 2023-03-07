package com.ighe3.api.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanChangeStatusIto {
    @JsonProperty("payman_id")
    String paymanId;
    // TODO: handle enum
    @JsonProperty("new_status")
    String newStatus;
}
