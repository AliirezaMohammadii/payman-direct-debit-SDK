package com.ighe3.api.dal.dto.input;

import lombok.Data;

@Data
public class ChangeStatusInputDTO {
    String paymanId;
    // TODO: handle enum
    String newStatus;
}