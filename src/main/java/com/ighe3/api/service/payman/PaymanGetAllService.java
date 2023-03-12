package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.ListRequest;
import com.ighe3.api.dto.provider.response.PaymanListResponse;
import com.ighe3.api.exception.BaseException;

public interface PaymanGetAllService {

    PaymanListResponse getList(ListRequest inputDto) throws BaseException;
}
