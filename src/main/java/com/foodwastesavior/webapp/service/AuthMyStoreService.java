package com.foodwastesavior.webapp.service;

import com.foodwastesavior.webapp.request.RegisterMyStoreRequest;
import com.foodwastesavior.webapp.response.RegisterMyStoreResponse;

public interface AuthMyStoreService {
    RegisterMyStoreResponse googleOAuthMyStore(RegisterMyStoreRequest registerMyStoreRequest);
}
