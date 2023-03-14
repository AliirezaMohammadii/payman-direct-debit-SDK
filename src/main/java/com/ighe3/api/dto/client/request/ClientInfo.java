package com.ighe3.api.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClientInfo {

    @JsonProperty("client_device_id")
    private String clientDeviceId;

    @JsonProperty("client_ip_address")
    private String clientIpAddress;

    @JsonProperty("client_user_agent")
    private String clientUserAgent;

    @JsonProperty("client_user_id")
    private String clientUserId;

    @JsonProperty("client_platform_type")
    private String clientPlatformType;
}
