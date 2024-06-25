package com.campuslands.modules.auth.application;


import java.util.Optional;


import com.campuslands.modules.auth.domain.models.Auth;
import com.campuslands.modules.auth.domain.repository.AuthRepository;

public class AuthService {
    
    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    
    public Optional<Auth> login(String email, String pas) {
        return authRepository.login(email, pas);
    }

    public void getRol(){

    }
}
