package com.payman.api.service.payman;

import com.payman.api.dto.client.response.TraceCreationResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TraceCreationService {

    TraceCreationResponse trace(HttpServletRequest httpServletRequest, String traceId) throws IOException;
}
