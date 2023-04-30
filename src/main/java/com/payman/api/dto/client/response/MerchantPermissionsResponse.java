package com.payman.api.dto.client.response;

import com.payman.api.dto.client.MerchantPermissionDetails;
import com.payman.api.mapper.JsonMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MerchantPermissionsResponse {

    private List<MerchantPermissionDetails> permissions;

    public MerchantPermissionsResponse(List<HashMap<String, Object>> paymanResponse) {
        this.permissions = Optional.ofNullable(paymanResponse).orElse(Collections.emptyList())
                .stream().map(this::mapHashMapItemToResponseResult).collect(Collectors.toList());
    }

    private MerchantPermissionDetails mapHashMapItemToResponseResult(HashMap<String, Object> item) {
        String json = JsonMapper.mapJavaObjectToJson(item);
        return (MerchantPermissionDetails) JsonMapper.mapJsonToJavaObject(json, MerchantPermissionDetails.class);
    }
}
