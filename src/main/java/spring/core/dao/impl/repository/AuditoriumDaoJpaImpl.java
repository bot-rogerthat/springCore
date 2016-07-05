package spring.core.dao.impl.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.core.dao.AuditoriumDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Auditorium;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AuditoriumDaoJpaImpl implements AuditoriumDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void create(Auditorium target) throws DaoException {
        em.persist(target);
    }

    @Override
    @Transactional
    public void update(Auditorium target) throws DaoException {
        em.merge(target);
    }

    @Override
    @Transactional
    public void delete(Auditorium target) throws DaoException {
        em.remove(em.contains(target) ? target : em.merge(target));
    }

    @Override
    public Auditorium getById(int id) throws DaoException {
        return em.find(Auditorium.class, id);
    }

    @Override
    public List<Auditorium> getAll() throws DaoException {
        return em.createQuery("from Auditorium", Auditorium.class).getResultList();
    }

}
