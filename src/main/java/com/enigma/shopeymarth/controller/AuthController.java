package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.dto.auth.request.AuthRequest;
import com.enigma.shopeymarth.dto.auth.response.LoginResponse;
import com.enigma.shopeymarth.dto.auth.response.RegisterResponse;
import com.enigma.shopeymarth.dto.response.CommondResponseAuth;
import com.enigma.shopeymarth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody AuthRequest authRequest) {
        RegisterResponse registerCustomer = authService.registerCustomer(authRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommondResponseAuth.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Success create account customer")
                        .data(registerCustomer)
                        .build()
        );
    }
    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AuthRequest authRequest) {
        RegisterResponse registerResponse = authService.registerAdmin(authRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommondResponseAuth.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Success create account admin")
                        .data(registerResponse)
                        .build());
    }

//    @PostMapping("/register/admin")
//    public RegisterResponse registerAdmin(@RequestBody AuthRequest authRequest) {
//        System.out.println("Hello");
//        return authService.registerAdmin(authRequest);
//    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        LoginResponse loginResponse = authService.login(authRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommondResponseAuth.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success login")
                        .data(loginResponse)
                        .build()
        );
    }
}
