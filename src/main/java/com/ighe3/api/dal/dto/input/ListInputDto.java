package com.ighe3.api.dal.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ListInputDto {
    private Integer offset;
    private Integer length;
    @JsonProperty("filter")
    private FilterObjectInputDto filterObject;
}
