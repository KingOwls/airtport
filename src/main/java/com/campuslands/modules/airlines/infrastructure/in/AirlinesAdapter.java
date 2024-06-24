package com.campuslands.modules.airlines.infrastructure.in;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

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
        // Traer

        JButton addButton = new JButton("Buscar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                } catch (Exception a) {

                }
            }
        });

        v.printBody(addButton, v.BackButton());
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