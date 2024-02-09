package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TraceCreationRequest;
import com.ighe3.api.dto.client.response.TraceCreationResponse;

import java.io.IOException;

public interface TraceCreationService {

    TraceCreationResponse trace(TraceCreationRequest request) throws IOException;
}
