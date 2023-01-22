package com.ighe3.api.dal.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.model.FilterObject;
import lombok.Data;

@Data
public class TransactionsInputDTO {
    private Integer offset;
    private Integer length;
    @JsonProperty("filter")
    private FilterObject filterObject;
}
