package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ighe3.api.dto.client.request.ClientInfo;
import lombok.Data;

@Data
public class SourceInfo {

    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("client_info")
    private ClientInfo clientInfo;
}
