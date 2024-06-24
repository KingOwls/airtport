package com.campuslands.modules.tripulationroles.infrastructure.in;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;


import com.campuslands.modules.tripulationroles.application.TripulationrolesService;
import com.campuslands.modules.tripulationroles.domain.models.Tripulationroles;

import com.campuslands.views.infrastructure.out.ViewOut;


/**
 * TripulationrolesAdapter
 */
public class TripulationrolesAdapter {

    ViewOut v;
    Tripulationroles tripulationroles;

     private final TripulationrolesService tripulationrolesService;

    public TripulationrolesAdapter(TripulationrolesService tripulationrolesService) {

        this.tripulationrolesService = tripulationrolesService;

    }

    public void newTR(){
        v = new ViewOut();
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre", 30);
        
        JButton addButton = new JButton("Agregar Nueva Tripulacion");
         addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        tripulationrolesService.createTripulationroles(new Tripulationroles(nameInput.getText()));
                        //v.backButton();
                    } catch (Exception a) {
                        
                    }
                }
            });

        v.container.add(nameInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }


    

    
}