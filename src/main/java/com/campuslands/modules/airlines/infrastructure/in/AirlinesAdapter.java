package com.campuslands.modules.airlines.infrastructure.in;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.util.List;
import java.util.Optional;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import com.campuslands.modules.airlines.domain.models.Airlines;
import com.campuslands.views.infrastructure.out.ViewOut;
import com.campuslands.modules.airlines.application.AirlinesService;

/**
 * AirlinesAdapter
 */
public class AirlinesAdapter {

    ViewOut v;
    private final AirlinesService airlinesService;

    public AirlinesAdapter(AirlinesService airlinesService) {
        this.airlinesService = airlinesService;
    }

    public void createAirline() {
        v = new ViewOut();
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre de la Aerolínea", 30);

        JButton addButton = new JButton("Agregar Nueva Aerolínea");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Airlines airlines = new Airlines(nameInput.getText());
                    airlinesService.createAirline(airlines);
                } catch (Exception a) {

                }
            }
        });

        v.container.add(nameInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateAirline() {
        v = new ViewOut();

        ViewOut.VInput idInput = v.new VInput("Ingresa el id de la Aerolínea", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre de la Aerolínea", 30);

        JButton addButton = new JButton("Agregar Nueva Aerolínea");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Airlines airlines = new Airlines(idInput.getInt(), nameInput.getText());
                    airlinesService.updateAirline(airlines);
                } catch (Exception a) {

                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());

        v.printBody(addButton, v.BackButton());
    }

    public void deleteAirline() {
        v = new ViewOut();

        ViewOut.VInput idInput = v.new VInput("Ingresa el id de la Aerolínea", 30);

        JButton addButton = new JButton("Borrar Aerolínea");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    airlinesService.deleteAirline(idInput.getInt());
                } catch (Exception a) {

                }
            }
        });

        v.container.add(idInput.getDiv());

        v.printBody(addButton, v.BackButton());
    }

    public void findAirlineAll() {
        v = new ViewOut();
        List<Airlines> airlines = airlinesService.getAllAirlines();
        String[] columnNames = { "ID", "Nombre" };
        Object[][] data = new Object[airlines.size()][2];

        for (int i = 0; i < airlines.size(); i++) {
            Airlines airline = airlines.get(i);
            data[i][0] = airline.getId();
            data[i][1] = airline.getName();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }

    public void findByIdAirline() {
        try {
            v = new ViewOut();
            ViewOut.VInput idInput = v.new VInput("Ingresa el ID de la Aerolínea a Buscar", 30);

            JButton searchButton = new JButton("Buscar Aerolínea");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JPanel lastWindow = v.body; // Store the previous window panel.
                        v = new ViewOut(); // Create a new instance of ViewOut
                        int id = idInput.getInt();
                        Optional<Airlines> airlineOptional = airlinesService.getAirlineById(id);
                        if (airlineOptional.isPresent()) {
                            Airlines airline = airlineOptional.get();
                            String[] columnNames = { "ID", "Nombre" };
                            Object[][] data = new Object[1][2];
                            data[0][0] = airline.getId();
                            data[0][1] = airline.getName();

                            v.container.add(v.new VTable(columnNames, data).getDiv());
                            v.printBody(v.BackButton("findByIdAirline", lastWindow));
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró la aerolínea con el ID especificado",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Ingrese un valor numérico para el ID de la aerolínea",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(v.container, "Error al buscar la aerolínea: " + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            v.container.add(idInput.getDiv());
            v.printBody(searchButton, v.BackButton());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la búsqueda de aerolínea: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

/**
 * public void start() {
 * 
 * Scanner scanner = new Scanner(System.in);
 * 
 * while (true) {
 * System.out.println("1. Crear Aerolinea");
 * System.out.println("2. Actualizar Aerolinea");
 * System.out.println("3. Buscar Aerolinea por ID");
 * System.out.println("4. Eliminar Aerolinea");
 * System.out.println("5. Listar todos Aerolinea");
 * System.out.println("6. Salir");
 * System.out.println("");
 * System.out.print("Ingrese la opcion: ");
 * int choice = scanner.nextInt();
 * scanner.nextLine(); // Consume newline
 * 
 * switch (choice) {
 * case 1:
 * System.out.print("Ingrese el nombre de la aerolinea: ");
 * String createName = scanner.nextLine();
 * 
 * Airlines newAirline = new Airlines(createName);
 * airlinesService.createAirline(newAirline);
 * break;
 * 
 * case 2:
 * System.out.print("Ingrese ID a actualizar: ");
 * int updateId = scanner.nextInt();
 * scanner.nextLine();
 * System.out.print("Ingrese el nuevo nombre: ");
 * String updateName = scanner.nextLine();
 * 
 * Airlines updatedAirline = new Airlines(updateId, updateName);
 * airlinesService.updateAirline(updatedAirline);
 * break;
 * 
 * case 3:
 * System.out.print("Ingrese el Id del aerolinea a buscar: ");
 * int findId = scanner.nextInt();
 * scanner.nextLine();
 * 
 * Optional<Airlines> airline = airlinesService.getAirlineById(findId);
 * airline.ifPresentOrElse(
 * a -> System.out.println("ID: " + a.getId() + ", Nombre: " + a.getName()),
 * () -> System.out.println("aerolinea no encontrada"));
 * break;
 * 
 * case 4:
 * System.out.print("Ingrese el Id del aerolinea a borrar: ");
 * int deleteId = scanner.nextInt();
 * scanner.nextLine();
 * airlinesService.deleteAirline(deleteId);
 * break;
 * 
 * case 5:
 * airlinesService.getAllAirlines().forEach(a -> {
 * System.out.println("ID: " + a.getId() + ", name: " + a.getName());
 * });
 * break;
 * 
 * case 6:
 * scanner.close();
 * System.exit(0);
 * break;
 * 
 * default:
 * System.out.println("Opcion invalida, intentelo de nuevo.");
 * }
 * }
 * }
 */