package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.dto.auth.request.AuthRequest;
import com.enigma.shopeymarth.dto.auth.response.LoginResponse;
import com.enigma.shopeymarth.dto.auth.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(AuthRequest request);
    RegisterResponse registerAdmin(AuthRequest request);
    LoginResponse login(AuthRequest authRequest);
}
