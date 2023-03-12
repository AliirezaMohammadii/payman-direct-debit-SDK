package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.ChangeStatusRequest;
import com.ighe3.api.dto.provider.response.PaymanChangeStatusResponse;
import com.ighe3.api.exception.BaseException;

public interface PaymanChangeStatusService {

    PaymanChangeStatusResponse changeStatus(ChangeStatusRequest inputDto) throws BaseException;
}
