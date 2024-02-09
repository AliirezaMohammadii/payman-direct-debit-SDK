package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.ChangeStatusRequest;
import com.ighe3.api.dto.client.response.ChangeStatusResponse;

import java.io.IOException;

public interface ChangeStatusService {

    ChangeStatusResponse changeStatus(ChangeStatusRequest request) throws IOException;
}
