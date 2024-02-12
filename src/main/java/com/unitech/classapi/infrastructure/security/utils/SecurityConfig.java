package com.unitech.classapi.infrastructure.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityConfig {

    public static String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
