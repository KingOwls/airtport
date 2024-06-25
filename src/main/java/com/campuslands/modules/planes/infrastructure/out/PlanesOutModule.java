package com.campuslands.modules.planes.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.planes.application.PlanesService;
import com.campuslands.modules.planes.infrastructure.in.PlanesAdapter;

public class PlanesOutModule {

    protected PlanesMySqlRepository planesMySQL;
    protected PlanesService planesService;
    protected PlanesAdapter planesAdapter;

    public PlanesOutModule() {
        planesMySQL = new PlanesMySqlRepository(); // Initialize PlanesMySQL instance
        planesService = new PlanesService(planesMySQL); // Initialize PlanesService with PlanesMySQL
        planesAdapter = new PlanesAdapter(planesService); // Initialize PlanesAdapter with PlanesService
    }

    public PlanesAdapter module() {
        return planesAdapter; // Return the PlanesAdapter instance
    }

    public JMenu options() {
        JMenu option = new JMenu("Aviones");
        option.add(new JMenuItem(new AbstractAction("Registrar Avion") {
            @Override
            public void actionPerformed(ActionEvent e) {
                planesAdapter.createPlane();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Avion") {
            @Override
            public void actionPerformed(ActionEvent e) {
                planesAdapter.updatePlane();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Avion") {
            @Override
            public void actionPerformed(ActionEvent e) {
                planesAdapter.deletePlane();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Avion") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();

            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Avion") {
            @Override
            public void actionPerformed(ActionEvent e) {
                planesAdapter.findAllPlanes();
            }
        }));

        return option;
    }
}
