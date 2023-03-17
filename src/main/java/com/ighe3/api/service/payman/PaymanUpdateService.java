package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.UpdateRequest;
import com.ighe3.api.dto.client.response.UpdateResponse;
import com.ighe3.api.exception.BaseException;

import java.io.IOException;

public interface PaymanUpdateService {

    UpdateResponse update(UpdateRequest request) throws IOException;
}
