package com.ighe3.api.service.payman;

import com.ighe3.api.dto.provider.response.PaymanTraceCreateResponse;
import com.ighe3.api.exception.BaseException;

public interface TraceCreateService {

    PaymanTraceCreateResponse trace(String traceId) throws BaseException;
}
