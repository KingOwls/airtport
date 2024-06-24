package com.campuslands.modules.airlines.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.airlines.application.AirlinesService;
import com.campuslands.modules.airlines.infrastructure.in.AirlinesAdapter;

public class AirlinesOutModule {

    protected AirlinesMySQL AirlinesRepository;
    protected AirlinesService service;
    protected AirlinesAdapter adapter;

    public AirlinesOutModule() {
        AirlinesRepository = new AirlinesMySQL();
        service = new AirlinesService(AirlinesRepository);
        adapter = new AirlinesAdapter(service);

    }

    public AirlinesAdapter module() {
        return adapter;
    }

    public JMenu options() {
        JMenu option = new JMenu("Aerolíneas");
        option.add(new JMenuItem(new AbstractAction("Registrar Aeropuertos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.createAirline();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Aeropuertos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.updateAirline();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Aeropuertos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.deleteAirline();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Aeropuerto") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Aeropuertos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.findAirlineAll();
            }
        }));

        return option;
    }
}
