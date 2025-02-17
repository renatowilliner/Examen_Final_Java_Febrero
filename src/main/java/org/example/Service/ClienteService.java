package org.example.Service;

import java.util.List;
import org.example.Model.Cliente;
import org.example.Repository.ClienteDAO;

public class ClienteService {
    private ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void CrearCliente(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (cliente.getApellido() == null || cliente.getApellido().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        if (cliente.getTelefono() == null || cliente.getTelefono().length() < 10) {
            throw new IllegalArgumentException("El telefono debe tener al menos 10 dígitos");
        }
        if (cliente.getEmail() == null || !cliente.getEmail().contains("@")) {
            throw new IllegalArgumentException("El correo electronico no es válido");
        }
        if (cliente.getVehiculo() == null || cliente.getVehiculo().isEmpty()) {
            throw new IllegalArgumentException("El vehiculo es obligatorio");
        }

        clienteDAO.CrearCliente(cliente);
    }

    public List<Cliente> ObtenerClientes() {
        return clienteDAO.ObtenerClientes();
    }

    public void ActualizarCliente(Integer idCliente, String nombre, String apellido, String telefono, String email, String vehiculo) {
        if (idCliente == null || idCliente <= 0) {
            throw new IllegalArgumentException("ID de cliente no valido");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        if (telefono == null || telefono.length() < 10) {
            throw new IllegalArgumentException("El telefono debe tener al menos 10 dígitos");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El correo electronico no es válido");
        }
        if (vehiculo == null || vehiculo.isEmpty()) {
            throw new IllegalArgumentException("El vehiculo es obligatorio");
        }

        clienteDAO.ActualizarCliente(idCliente, nombre, apellido, telefono, email, vehiculo);
    }

    public void EliminarCliente(Integer idCliente) {
        if (idCliente == null || idCliente <= 0) {
            throw new IllegalArgumentException("ID de cliente no valido");
        }

        clienteDAO.EliminarCliente(idCliente);
    }
}
