package spring.core.dao.impl.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.core.dao.DiscountStatDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.DiscountStat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DiscountStatDaoJpaIml implements DiscountStatDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void create(DiscountStat target) throws DaoException {
        em.persist(target);
    }

    @Override
    @Transactional
    public void update(DiscountStat target) throws DaoException {
        em.merge(target);
    }

    @Override
    @Transactional
    public void delete(DiscountStat target) throws DaoException {
        em.remove(em.contains(target) ? target : em.merge(target));
    }

    @Override
    public DiscountStat getById(int id) throws DaoException {
        return em.find(DiscountStat.class, id);
    }

    @Override
    @Transactional
    public List<DiscountStat> getAll() throws DaoException {
        return em.createQuery("from DiscountStat", DiscountStat.class).getResultList();
    }
}
