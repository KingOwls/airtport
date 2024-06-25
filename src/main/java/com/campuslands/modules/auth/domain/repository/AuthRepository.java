package com.campuslands.modules.auth.domain.repository;

import java.util.Optional;

import com.campuslands.modules.auth.domain.models.Auth;

public interface AuthRepository {

    Optional<Auth> login(String email, String password);

}
