package com.campuslands.views.domain.repository;


import com.campuslands.views.domain.models.View;

public interface ViewsRepository {

    View mainFrame();
    void fire();
    void close();


    
}
