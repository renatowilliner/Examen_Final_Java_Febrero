
package org.example.Service;

import java.util.List;
import org.example.Model.OrdenReparacion;
import org.example.Repository.OrdenReparacionDAO;

public class OrdenReparacionService{
    private OrdenReparacionDAO ordenDAO;

    public OrdenReparacionService(OrdenReparacionDAO ordenDAO) {
        this.ordenDAO = ordenDAO;
    }

    public void CrearOrden(OrdenReparacion orden) {

        if (orden.getCliente() == null) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }

        if (orden.getEstado() == null || (!orden.getEstado().equals("Pendiente") && 
                                           !orden.getEstado().equals("En Proceso") && 
                                           !orden.getEstado().equals("Finalizada") &&
                                           !orden.getEstado().equals("Cancelada"))) {
            throw new IllegalArgumentException("Estado inválido");
        }
        
        ordenDAO.CrearOrden(orden);
    }

    public void ActualizarOrden(Integer idOrden, String estado, String descripcionProblema) {
        if (idOrden == null || idOrden <= 0) {
            throw new IllegalArgumentException("ID de orden nulo");
        }

        if (estado == null || (!estado.equals("Pendiente") && 
                               !estado.equals("En Proceso") && 
                               !estado.equals("Finalizada") &&
                               !estado.equals("Cancelada"))) {
            throw new IllegalArgumentException("Estado invalido.");
        }

        ordenDAO.ActualizarOrden(idOrden, estado, descripcionProblema);
    }

    public void EliminarOrden(Integer idOrden) {

        if (idOrden == null || idOrden <= 0) {
            throw new IllegalArgumentException("ID de orden nulo");
        }

        ordenDAO.EliminarOrden(idOrden);
    }
    public List<OrdenReparacion> ObtenerOrdenesFiltro(String estado, String fechaInicio, String fechaFin, String descripcionProblema) {

        if (estado != null && !estado.matches("Pendiente|En Proceso|Finalizada|Cancelada")) {
            throw new IllegalArgumentException("Estado invalido");
        }

        if (fechaInicio != null && fechaFin != null) {
            if (fechaInicio.compareTo(fechaFin) > 0) {
                throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
            }
        }
        return ordenDAO.ObtenerOrdenes(estado, fechaInicio, fechaFin, descripcionProblema);
    }
}





