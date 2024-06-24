package com.campuslands.modules.tripulationroles.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.tripulationroles.application.TripulationrolesService;

import com.campuslands.modules.tripulationroles.infrastructure.in.TripulationrolesAdapter;

public class TripulationrolesOutModule {

    

        protected TripulationRolesMySQL tripulationRolesMySqlRepository;
        protected TripulationrolesService service;
        protected TripulationrolesAdapter adapter;

        public TripulationrolesOutModule(){
            tripulationRolesMySqlRepository = new TripulationRolesMySQL();
            service = new TripulationrolesService(tripulationRolesMySqlRepository);
            adapter = new TripulationrolesAdapter(service);

        }

        public TripulationrolesAdapter module(){
            return adapter;
        }


        

        public JMenu options(){
            JMenu option = new JMenu("Aviones");
            option.add(new JMenuItem(new AbstractAction("Registrar Avion") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adapter.newTR();
                }
            }));

        
            option.add(new JMenuItem(new AbstractAction("Actualizar Avión") {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            }));

            
        
            option.add(new JMenuItem(new AbstractAction("Eliminar Avión") {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            }));

            return option;
        }
}
