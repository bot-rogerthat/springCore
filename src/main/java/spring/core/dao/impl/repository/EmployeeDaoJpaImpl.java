package spring.core.dao.impl.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.core.dao.EmployeeDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void create(Employee target) throws DaoException {
        em.persist(target);
    }

    @Override
    @Transactional
    public void update(Employee target) throws DaoException {
        em.merge(target);
    }

    @Override
    @Transactional
    public void delete(Employee target) throws DaoException {
        em.remove(em.contains(target) ? target : em.merge(target));
    }

    @Override
    public Employee getById(int id) throws DaoException {
        return em.find(Employee.class, id);
    }

    @Override
    public List<Employee> getAll() throws DaoException {
        return em.createQuery("from Employee", Employee.class).getResultList();
    }
}
