package com.ighe3.api.service.payman;

import com.ighe3.api.dto.provider.response.PaymanTracePayResponse;
import com.ighe3.api.exception.BaseException;

public interface TracePayService {

    PaymanTracePayResponse trace(String traceId, String date) throws BaseException;
}
