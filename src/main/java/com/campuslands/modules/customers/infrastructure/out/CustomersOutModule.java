package com.campuslands.modules.customers.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.customers.application.CustomersService;
import com.campuslands.modules.customers.infrastructure.in.CustomersAdapter;

public class CustomersOutModule {

    protected CustomersMySQL customersMySQL;
    protected CustomersService customersService;
    protected CustomersAdapter customersAdapter;

    public CustomersOutModule() {
        customersMySQL = new CustomersMySQL();
        customersService = new CustomersService(customersMySQL);
        customersAdapter = new CustomersAdapter(customersService);
    }

    public CustomersAdapter module() {
        return customersAdapter;
    }

    public JMenu options() {
        JMenu option = new JMenu("Clientes");
        option.add(new JMenuItem(new AbstractAction("Registrar Cliente") {
            @Override
            public void actionPerformed(ActionEvent e) {
                customersAdapter.createCustomer();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Cliente") {
            @Override
            public void actionPerformed(ActionEvent e) {
                customersAdapter.updateCustomer();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Cliente") {
            @Override
            public void actionPerformed(ActionEvent e) {
                customersAdapter.deleteCustomer();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Cliente") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Cliente") {
            @Override
            public void actionPerformed(ActionEvent e) {
                customersAdapter.findAllCustomers();
            }
        }));

        return option;
    }
}
