package spring.core.dao.impl.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.core.dao.TicketDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TicketDaoJpaImpl implements TicketDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void create(Ticket target) throws DaoException {
        em.persist(target);
    }

    @Override
    @Transactional
    public void update(Ticket target) throws DaoException {
        em.merge(target);
    }

    @Override
    @Transactional
    public void delete(Ticket target) throws DaoException {
        em.remove(em.contains(target) ? target : em.merge(target));
    }

    @Override
    public Ticket getById(int id) throws DaoException {
        return em.find(Ticket.class, id);
    }

    @Override
    @Transactional
    public List<Ticket> getAll() throws DaoException {
        return em.createQuery("from Ticket", Ticket.class).getResultList();
    }
}