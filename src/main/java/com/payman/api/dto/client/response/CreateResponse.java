package com.payman.api.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateResponse {

    @JsonProperty("redirect_url")
    private String redirectUrl;
}
