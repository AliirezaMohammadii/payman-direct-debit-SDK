package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.GetPaymanRequest;
import com.ighe3.api.dto.client.response.GetPaymanResponse;

import java.io.IOException;

public interface GetPaymanService {

    GetPaymanResponse get(GetPaymanRequest request) throws IOException;
}
