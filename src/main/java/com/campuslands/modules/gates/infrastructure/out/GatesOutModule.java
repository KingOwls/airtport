package com.campuslands.modules.gates.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.gates.application.GatesService;
import com.campuslands.modules.gates.infrastructure.in.GatesAdapter;

public class GatesOutModule {

    protected GatesMySQL gatesMySQL;
    protected GatesService gatesService;
    protected GatesAdapter gatesAdapter;

    public GatesOutModule() {
        gatesMySQL = new GatesMySQL();
        gatesService = new GatesService(gatesMySQL);
        gatesAdapter = new GatesAdapter(gatesService);
    }

    public GatesAdapter module() {
        return gatesAdapter;
    }

    public JMenu options() {
        JMenu option = new JMenu("Tipo de puertas");
        option.add(new JMenuItem(new AbstractAction("Registrar Tipo de puertas") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.createEmployee();
                gatesAdapter.createGate();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Tipo de puertas") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.updateEmployee();
                gatesAdapter.updateGate();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Tipo de puertas") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.deleteEmployee();
                gatesAdapter.deleteGate();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Tipo de puertas") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Tipo de puertas") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.findAllEmployees();
                gatesAdapter.findAllGates();
            }
        }));

        return option;
    }
}
