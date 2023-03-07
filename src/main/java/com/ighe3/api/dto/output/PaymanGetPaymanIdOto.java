package com.ighe3.api.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymanGetPaymanIdOto {
    @JsonProperty("payman_id")
    private String paymanId;
    private String status;
    private String deposit;
}
