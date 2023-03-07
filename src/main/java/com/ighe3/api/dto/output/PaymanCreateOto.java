package com.ighe3.api.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymanCreateOto {
    @JsonProperty("redirect_url")
    private String redirectUrl;
}
