package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.CreateRequest;
import com.ighe3.api.dto.client.response.CreateResponse;

import java.io.IOException;

public interface CreateService {

    CreateResponse create(CreateRequest request) throws IOException;
}
