package com.ighe3.api.dal.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOutputDto {
    @JsonProperty("redirect_url")
    private String redirectUrl;
}
