package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    AppUser loadUserByUserId(String id);
    UserDetails loadUserByUsername(String username);
}
