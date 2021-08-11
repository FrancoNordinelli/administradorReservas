
package Persistencia;

import Logica.Huesped;
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

public class HuespedJpaController implements Serializable {

    public HuespedJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public HuespedJpaController() {
        emf = Persistence.createEntityManagerFactory("Nordinelli_Franco_proyectoPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Huesped huesped) {
        if (huesped.getListaDeReservas() == null) {
            huesped.setListaDeReservas(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedListaDeReservas = new ArrayList<Reserva>();
            for (Reserva listaDeReservasReservaToAttach : huesped.getListaDeReservas()) {
                listaDeReservasReservaToAttach = em.getReference(listaDeReservasReservaToAttach.getClass(), listaDeReservasReservaToAttach.getId());
                attachedListaDeReservas.add(listaDeReservasReservaToAttach);
            }
            huesped.setListaDeReservas(attachedListaDeReservas);
            em.persist(huesped);
            for (Reserva listaDeReservasReserva : huesped.getListaDeReservas()) {
                Huesped oldHuespedOfListaDeReservasReserva = listaDeReservasReserva.getHuesped();
                listaDeReservasReserva.setHuesped(huesped);
                listaDeReservasReserva = em.merge(listaDeReservasReserva);
                if (oldHuespedOfListaDeReservasReserva != null) {
                    oldHuespedOfListaDeReservasReserva.getListaDeReservas().remove(listaDeReservasReserva);
                    oldHuespedOfListaDeReservasReserva = em.merge(oldHuespedOfListaDeReservasReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Huesped huesped) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Huesped persistentHuesped = em.find(Huesped.class, huesped.getIdPersona());
            List<Reserva> listaDeReservasOld = persistentHuesped.getListaDeReservas();
            List<Reserva> listaDeReservasNew = huesped.getListaDeReservas();
            List<Reserva> attachedListaDeReservasNew = new ArrayList<Reserva>();
            for (Reserva listaDeReservasNewReservaToAttach : listaDeReservasNew) {
                listaDeReservasNewReservaToAttach = em.getReference(listaDeReservasNewReservaToAttach.getClass(), listaDeReservasNewReservaToAttach.getId());
                attachedListaDeReservasNew.add(listaDeReservasNewReservaToAttach);
            }
            listaDeReservasNew = attachedListaDeReservasNew;
            huesped.setListaDeReservas(listaDeReservasNew);
            huesped = em.merge(huesped);
            for (Reserva listaDeReservasOldReserva : listaDeReservasOld) {
                if (!listaDeReservasNew.contains(listaDeReservasOldReserva)) {
                    listaDeReservasOldReserva.setHuesped(null);
                    listaDeReservasOldReserva = em.merge(listaDeReservasOldReserva);
                }
            }
            for (Reserva listaDeReservasNewReserva : listaDeReservasNew) {
                if (!listaDeReservasOld.contains(listaDeReservasNewReserva)) {
                    Huesped oldHuespedOfListaDeReservasNewReserva = listaDeReservasNewReserva.getHuesped();
                    listaDeReservasNewReserva.setHuesped(huesped);
                    listaDeReservasNewReserva = em.merge(listaDeReservasNewReserva);
                    if (oldHuespedOfListaDeReservasNewReserva != null && !oldHuespedOfListaDeReservasNewReserva.equals(huesped)) {
                        oldHuespedOfListaDeReservasNewReserva.getListaDeReservas().remove(listaDeReservasNewReserva);
                        oldHuespedOfListaDeReservasNewReserva = em.merge(oldHuespedOfListaDeReservasNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = huesped.getIdPersona();
                if (findHuesped(id) == null) {
                    throw new NonexistentEntityException("The huesped with id " + id + " no longer exists.");
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
            Huesped huesped;
            try {
                huesped = em.getReference(Huesped.class, id);
                huesped.getIdPersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The huesped with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> listaDeReservas = huesped.getListaDeReservas();
            for (Reserva listaDeReservasReserva : listaDeReservas) {
                listaDeReservasReserva.setHuesped(null);
                listaDeReservasReserva = em.merge(listaDeReservasReserva);
            }
            em.remove(huesped);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Huesped> findHuespedEntities() {
        return findHuespedEntities(true, -1, -1);
    }

    public List<Huesped> findHuespedEntities(int maxResults, int firstResult) {
        return findHuespedEntities(false, maxResults, firstResult);
    }

    private List<Huesped> findHuespedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Huesped.class));
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

    public Huesped findHuesped(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Huesped.class, id);
        } finally {
            em.close();
        }
    }

    public int getHuespedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Huesped> rt = cq.from(Huesped.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
