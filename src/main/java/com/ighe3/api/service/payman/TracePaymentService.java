package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.TracePaymentRequest;
import com.ighe3.api.dto.client.response.TracePaymentResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TracePaymentService {

    TracePaymentResponse trace(HttpServletRequest httpServletRequest, String traceId, String date) throws IOException;
}
