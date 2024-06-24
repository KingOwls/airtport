package com.campuslands.views.application;

import javax.swing.JMenu;


import com.campuslands.views.domain.models.Header;

import com.campuslands.views.domain.models.View;
import com.campuslands.views.domain.repository.HeaderRepository;

import com.campuslands.views.domain.repository.ViewsRepository;

public class ViewsService  implements ViewsRepository, HeaderRepository{


    public ViewsService(){
        
    }


   
    

    @Override
    public void addOption(JMenu option) {
        Header.getInstance().addOption(option);
    }


    @Override
    public View mainFrame() {
        return View.getInstance();
    }

    @Override
    public void fire() {
        View.getInstance().fire();
    }

    @Override
    public void close() {
        View.getInstance().close();
    }



}
