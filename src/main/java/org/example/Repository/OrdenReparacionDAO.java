package org.example.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.criteria.Predicate;
import org.example.Model.OrdenReparacion;
import org.example.connections.HibernateUtil;
import org.hibernate.Session;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class OrdenReparacionDAO {
    public void CrearOrden(OrdenReparacion orden) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(orden);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<OrdenReparacion> ObtenerOrdenes() {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<OrdenReparacion> criteria = builder.createQuery(OrdenReparacion.class);
            Root<OrdenReparacion> root = criteria.from(OrdenReparacion.class);
            criteria.select(root);
            return session.createQuery(criteria).getResultList();
        }
    }

    public void ActualizarOrden(Integer idOrden, String estado, String descripcionProblema) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            OrdenReparacion orden = session.get(OrdenReparacion.class, idOrden);
            if (orden != null) {
                orden.setEstado(estado);
                orden.setDescripcionProblema(descripcionProblema);
                session.update(orden);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void EliminarOrden(Integer idOrden) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            OrdenReparacion orden = session.get(OrdenReparacion.class, idOrden);
            if (orden != null) {
                session.delete(orden);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<OrdenReparacion> ObtenerOrdenes(String estado, String fechaInicio, String fechaFin, String descripcionProblema) {
        try (Session session = HibernateUtil.getSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<OrdenReparacion> query = builder.createQuery(OrdenReparacion.class);
            Root<OrdenReparacion> root = query.from(OrdenReparacion.class);

            List<Predicate> predicates = new ArrayList<>();

            if (estado != null && !estado.isEmpty()) {
                predicates.add(builder.equal(root.get("estado"), estado));
            }

            if (fechaInicio != null && fechaFin != null) {
                predicates.add(builder.between(root.get("fechaIngreso"), fechaInicio, fechaFin));
            }
            else if (fechaInicio != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("fechaIngreso"), fechaInicio));
            }
            else if (fechaFin != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("fechaIngreso"), fechaFin));
            }

            if (descripcionProblema != null && !descripcionProblema.isEmpty()) {
                predicates.add(builder.like(root.get("descripcionProblema"), "%" + descripcionProblema + "%"));
            }

            if (!predicates.isEmpty()) {
                query.where(predicates.toArray(new Predicate[0]));
            }

            return session.createQuery(query).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}


