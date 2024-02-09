package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public abstract class BaseRequest {

    @JsonProperty("source_info")
    private SourceInfo sourceInfo;
}
