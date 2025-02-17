package org.example.Repository;

import java.util.List;
import org.example.Model.Cliente;
import org.example.connections.HibernateUtil;
import org.hibernate.Session;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class ClienteDAO {
    public void CrearCliente(Cliente cliente) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(cliente);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> ObtenerClientes() {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
            Root<Cliente> root = criteria.from(Cliente.class);
            criteria.select(root);
            return session.createQuery(criteria).getResultList();
        }
    }

    public void ActualizarCliente(Integer idCliente, String nombre, String apellido, String telefono, String email, String vehiculo) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Cliente cliente = session.get(Cliente.class, idCliente);
            if (cliente != null) {
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setTelefono(telefono);
                cliente.setEmail(email);
                cliente.setVehiculo(vehiculo);
                session.update(cliente);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void EliminarCliente(Integer idCliente) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Cliente cliente = session.get(Cliente.class, idCliente);
            if (cliente != null) {
                session.delete(cliente);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
