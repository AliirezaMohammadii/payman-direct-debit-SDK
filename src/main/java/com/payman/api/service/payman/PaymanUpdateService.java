package com.payman.api.service.payman;

import com.payman.api.dto.client.request.UpdateRequest;
import com.payman.api.dto.client.response.UpdateResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface PaymanUpdateService {

    UpdateResponse update(HttpServletRequest httpServletRequest, UpdateRequest request) throws IOException;
}
