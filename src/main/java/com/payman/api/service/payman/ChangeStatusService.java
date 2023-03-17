package com.payman.api.service.payman;

import com.payman.api.dto.client.request.ChangeStatusRequest;
import com.payman.api.dto.client.response.ChangeStatusResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ChangeStatusService {

    ChangeStatusResponse changeStatus(HttpServletRequest httpServletRequest, ChangeStatusRequest request) throws IOException;
}
