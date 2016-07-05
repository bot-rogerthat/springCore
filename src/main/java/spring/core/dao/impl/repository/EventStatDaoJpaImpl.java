package spring.core.dao.impl.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.core.dao.EventStatDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.EventStat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EventStatDaoJpaImpl implements EventStatDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void create(EventStat target) throws DaoException {
        em.persist(target);
    }

    @Override
    @Transactional
    public void update(EventStat target) throws DaoException {
        em.merge(target);
    }

    @Override
    @Transactional
    public void delete(EventStat target) throws DaoException {
        em.remove(em.contains(target) ? target : em.merge(target));
    }

    @Override
    public EventStat getById(int id) throws DaoException {
        return em.find(EventStat.class, id);
    }

    @Override
    @Transactional
    public List<EventStat> getAll() throws DaoException {
        return em.createQuery("from EventStat", EventStat.class).getResultList();
    }
}
