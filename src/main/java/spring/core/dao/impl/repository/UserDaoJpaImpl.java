package spring.core.dao.impl.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.core.dao.UserDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoJpaImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void create(User target) throws DaoException {
        em.persist(target);
    }

    @Override
    public void update(User target) throws DaoException {
        em.merge(target);
    }

    @Override
    @Transactional
    public void delete(int id) throws DaoException {
        em.remove(getById(id));
    }

    @Override
    public User getById(int id) throws DaoException {
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() throws DaoException {
        return em.createQuery("from User", User.class).getResultList();
    }
}
