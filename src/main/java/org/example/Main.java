package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.example.Model.Cliente;
import org.example.Model.OrdenReparacion;
import org.example.Repository.ClienteDAO;
import org.example.Repository.OrdenReparacionDAO;
import org.example.Service.ClienteService;
import org.example.Service.OrdenReparacionService;

public class Main {

    public static void main(String[] args) {

        ClienteService clienteService = new ClienteService(new ClienteDAO());
        OrdenReparacionService ordenService = new OrdenReparacionService(new OrdenReparacionDAO());


        // Crear una nueva orden de reparación
        try {
            Cliente cliente = new Cliente("Carlos", "Gomez", "3434", "carlos@gmail.com", "Ford Ka");

            // Usamos LocalDateTime para la fecha de creación
            OrdenReparacion nuevaOrden = new OrdenReparacion(cliente, LocalDateTime.now(), "Se rompió motor", "Pendiente");
            clienteService.CrearCliente(cliente);
            ordenService.CrearOrden(nuevaOrden);
            System.out.println("Orden de reparación creada con éxito.");
        } catch (IllegalArgumentException e) {
            System.out.println(" Error al crear la orden: " + e.getMessage());
        }

    }
}
