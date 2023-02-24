package com.ighe3.api.dal.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionsInputDto {
    private Integer offset;
    private Integer length;
    @JsonProperty("filter")
    private FilterObjectInputDto filterObject;
}
