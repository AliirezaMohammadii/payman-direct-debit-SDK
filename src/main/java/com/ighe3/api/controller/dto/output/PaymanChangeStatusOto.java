package com.ighe3.api.controller.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanChangeStatusOto {
    @JsonProperty("payman_id")
    private String paymanId;
    private String status;
}
