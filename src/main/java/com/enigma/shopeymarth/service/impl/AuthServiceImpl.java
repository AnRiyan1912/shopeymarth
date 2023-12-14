package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.constant.ERole;
import com.enigma.shopeymarth.dto.auth.request.AuthRequest;
import com.enigma.shopeymarth.dto.auth.response.LoginResponse;
import com.enigma.shopeymarth.dto.auth.response.RegisterResponse;
import com.enigma.shopeymarth.entity.*;
import com.enigma.shopeymarth.repository.AdminRepository;
import com.enigma.shopeymarth.repository.UserCredentialRepository;
import com.enigma.shopeymarth.security.JwtUtil;
import com.enigma.shopeymarth.service.AdminService;
import com.enigma.shopeymarth.service.AuthService;
import com.enigma.shopeymarth.service.CustomerService;
import com.enigma.shopeymarth.service.RoleService;
import com.enigma.shopeymarth.util.ValidationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final RoleService roleService;
    private final AdminService adminService;
    private final AdminRepository adminRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ValidationUtils validationUtils;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerCustomer(AuthRequest request) {
        try {

            //TODO 1 : set role
            Role role = Role.builder()
                    .name(ERole.ROLE_SELLER)
                    .build();
          role = roleService.getOrSave(role);

            //TODO 2 : set credential
            UserCredential userCredential = UserCredential.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);
            //TODO 3 : set customer
            Customer customer = Customer.builder()
                    .userCredential(userCredential)
                    .name(request.getCustomerName())
                    .address(request.getAddress())
                    .email(request.getEmail())
                    .mobilePhone(request.getMobilePhone())
                    .build();
            customerService.createNewCustomer(customer);
            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(userCredential.getRole().getName().toString())
                    .build();

        }catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exist!");
        }
    }
    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse registerAdmin(AuthRequest authRequest){
        Role role = Role.builder()
                .name(ERole.ROLE_ADMIN)
                .build();

        role = roleService.getOrSave(role);

        UserCredential userCredential = UserCredential.builder()
                .username(authRequest.getUsername())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .role(role)
                .build();
        userCredentialRepository.saveAndFlush(userCredential);

        Admin admin = Admin.builder()
                .name(authRequest.getCustomerName())
                .phoneNumber(authRequest.getMobilePhone())
                .userCredential(userCredential)
                .build();
        adminRepository.saveAndFlush(admin);
        return RegisterResponse.builder()
                .username(userCredential.getUsername())
                .role(role.getName().name())
                .build();
    }

    @Override
    public LoginResponse login(AuthRequest authRequest) {
        //tempat untuk logic login
        validationUtils.validation(authRequest);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //object appUser
        AppUser appUser = (AppUser)  authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);
        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .build();
    }
}
