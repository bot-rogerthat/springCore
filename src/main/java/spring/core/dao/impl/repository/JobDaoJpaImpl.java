package spring.core.dao.impl.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.core.dao.JobDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Job;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JobDaoJpaImpl implements JobDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public void create(Job target) throws DaoException {
        em.persist(target);
    }

    @Override
    @Transactional
    public void update(Job target) throws DaoException {
        em.merge(target);
    }

    @Override
    @Transactional
    public void delete(Job target) throws DaoException {
        em.remove(em.contains(target) ? target : em.merge(target));
    }

    @Override
    public Job getById(int id) throws DaoException {
        return em.find(Job.class, id);
    }

    @Override
    public List<Job> getAll() throws DaoException {
        return em.createQuery("from Job", Job.class).getResultList();
    }
}
