package com.campuslands.modules.auth.infrastructure.out;

import com.campuslands.modules.auth.application.AuthService;
import com.campuslands.modules.auth.domain.models.Auth;
import com.campuslands.modules.auth.infrastructure.in.AuthAdapter;

public class AuthOutModule {

    protected AuthMySQL MySQL;
    protected AuthService service;
    protected AuthAdapter adapter;

    public AuthOutModule() {
        MySQL = new AuthMySQL();
        service = new AuthService(MySQL);
        adapter = new AuthAdapter(service);

    }

    public AuthAdapter module() {
        return adapter;
    }

    public String getRol() {
        return Auth.getInstance().getRol();
    }

}
