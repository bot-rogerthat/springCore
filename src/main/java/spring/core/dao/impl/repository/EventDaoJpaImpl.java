package spring.core.dao.impl.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.core.dao.EventDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Event;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EventDaoJpaImpl implements EventDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void create(Event target) throws DaoException {
        em.persist(target);
    }

    @Override
    @Transactional
    public void update(Event target) throws DaoException {
        em.merge(target);
    }

    @Override
    @Transactional
    public void delete(Event target) throws DaoException {
        em.remove(em.contains(target) ? target : em.merge(target));
    }

    @Override
    public Event getById(int id) throws DaoException {
        return em.find(Event.class, id);
    }

    @Override
    @Transactional
    public List<Event> getAll() throws DaoException {
        return em.createQuery("from Event", Event.class).getResultList();
    }
}
