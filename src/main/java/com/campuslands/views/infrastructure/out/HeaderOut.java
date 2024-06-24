package com.campuslands.views.infrastructure.out;

import com.campuslands.views.application.ViewsService;
import com.campuslands.views.domain.models.Header;
import com.campuslands.views.domain.models.View;
import com.campuslands.views.infrastructure.in.ViewAdapter;

public class HeaderOut {

    ViewAdapter viewAdapter;

    public HeaderOut(){
        viewAdapter = new ViewAdapter(new ViewsService());
    }

    public void loadHeader(String rol){
        viewAdapter.header(rol);
        View.getInstance().addHeader(Header.getInstance().getMenuBar());
    }
    
}
