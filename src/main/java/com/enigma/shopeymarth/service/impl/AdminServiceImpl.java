package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.repository.AdminRepository;
import com.enigma.shopeymarth.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

}
