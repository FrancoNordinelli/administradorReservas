
package Persistencia;

import Logica.Habitacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Reserva;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HabitacionJpaController implements Serializable {

    public HabitacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public HabitacionJpaController() {
        emf = Persistence.createEntityManagerFactory("Nordinelli_Franco_proyectoPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Habitacion habitacion) {
        if (habitacion.getReservas() == null) {
            habitacion.setReservas(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedReservas = new ArrayList<Reserva>();
            for (Reserva reservasReservaToAttach : habitacion.getReservas()) {
                reservasReservaToAttach = em.getReference(reservasReservaToAttach.getClass(), reservasReservaToAttach.getId());
                attachedReservas.add(reservasReservaToAttach);
            }
            habitacion.setReservas(attachedReservas);
            em.persist(habitacion);
            for (Reserva reservasReserva : habitacion.getReservas()) {
                Habitacion oldHabitacionOfReservasReserva = reservasReserva.getHabitacion();
                reservasReserva.setHabitacion(habitacion);
                reservasReserva = em.merge(reservasReserva);
                if (oldHabitacionOfReservasReserva != null) {
                    oldHabitacionOfReservasReserva.getReservas().remove(reservasReserva);
                    oldHabitacionOfReservasReserva = em.merge(oldHabitacionOfReservasReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Habitacion habitacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitacion persistentHabitacion = em.find(Habitacion.class, habitacion.getId());
            List<Reserva> reservasOld = persistentHabitacion.getReservas();
            List<Reserva> reservasNew = habitacion.getReservas();
            List<Reserva> attachedReservasNew = new ArrayList<Reserva>();
            for (Reserva reservasNewReservaToAttach : reservasNew) {
                reservasNewReservaToAttach = em.getReference(reservasNewReservaToAttach.getClass(), reservasNewReservaToAttach.getId());
                attachedReservasNew.add(reservasNewReservaToAttach);
            }
            reservasNew = attachedReservasNew;
            habitacion.setReservas(reservasNew);
            habitacion = em.merge(habitacion);
            for (Reserva reservasOldReserva : reservasOld) {
                if (!reservasNew.contains(reservasOldReserva)) {
                    reservasOldReserva.setHabitacion(null);
                    reservasOldReserva = em.merge(reservasOldReserva);
                }
            }
            for (Reserva reservasNewReserva : reservasNew) {
                if (!reservasOld.contains(reservasNewReserva)) {
                    Habitacion oldHabitacionOfReservasNewReserva = reservasNewReserva.getHabitacion();
                    reservasNewReserva.setHabitacion(habitacion);
                    reservasNewReserva = em.merge(reservasNewReserva);
                    if (oldHabitacionOfReservasNewReserva != null && !oldHabitacionOfReservasNewReserva.equals(habitacion)) {
                        oldHabitacionOfReservasNewReserva.getReservas().remove(reservasNewReserva);
                        oldHabitacionOfReservasNewReserva = em.merge(oldHabitacionOfReservasNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = habitacion.getId();
                if (findHabitacion(id) == null) {
                    throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitacion habitacion;
            try {
                habitacion = em.getReference(Habitacion.class, id);
                habitacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> reservas = habitacion.getReservas();
            for (Reserva reservasReserva : reservas) {
                reservasReserva.setHabitacion(null);
                reservasReserva = em.merge(reservasReserva);
            }
            em.remove(habitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Habitacion> findHabitacionEntities() {
        return findHabitacionEntities(true, -1, -1);
    }

    public List<Habitacion> findHabitacionEntities(int maxResults, int firstResult) {
        return findHabitacionEntities(false, maxResults, firstResult);
    }

    private List<Habitacion> findHabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Habitacion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Habitacion findHabitacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Habitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Habitacion> rt = cq.from(Habitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
