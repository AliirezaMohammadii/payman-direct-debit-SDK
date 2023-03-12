package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.CreateRequest;
import com.ighe3.api.dto.client.response.CreateResponse;
import com.ighe3.api.exception.BaseException;

public interface PaymanCreateService {

    CreateResponse create(CreateRequest inputDto) throws BaseException;
}
