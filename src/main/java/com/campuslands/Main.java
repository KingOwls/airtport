package com.campuslands;

import com.campuslands.modules.auth.infrastructure.out.AuthOutModule;
import com.campuslands.views.domain.models.View;

public class Main {
    public static void main(String[] args) {

        AuthOutModule auth = new AuthOutModule();
        auth.module().loginView();
        View.getInstance().fire();

    }
}