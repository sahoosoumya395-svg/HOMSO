package com.homso.care.service;

import com.homso.care.dto.request.LoginRequest;
import com.homso.care.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}