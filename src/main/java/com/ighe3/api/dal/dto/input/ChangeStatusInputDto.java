package com.ighe3.api.dal.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChangeStatusInputDto {
    @JsonProperty("payman_id")
    String paymanId;
    // TODO: handle enum
    @JsonProperty("new_status")
    String newStatus;
}
