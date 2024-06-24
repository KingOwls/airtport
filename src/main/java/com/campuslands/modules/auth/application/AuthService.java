package com.campuslands.modules.auth.application;


import com.campuslands.modules.auth.domain.repository.AuthRepository;

public class AuthService {
    
    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    public void getRol(){

    }
}
