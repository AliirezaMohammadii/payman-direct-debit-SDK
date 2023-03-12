package com.ighe3.api.service.payman;

import com.ighe3.api.exception.BaseException;

public interface GetPaymanIdService {

    String getPaymanId(String paymanCode) throws BaseException;
}
