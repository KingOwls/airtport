package com.campuslands.modules.auth.infrastructure.out;

import com.campuslands.core.MySQL;

import com.campuslands.modules.auth.domain.repository.AuthRepository;

public class AuthMySQL extends MySQL implements AuthRepository  {

    @Override
    public String login() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
    
    
}
