package spring.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.core.dao.EmployeeDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Employee;
import spring.core.entity.Job;

import java.util.Collection;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public void create(Employee target) throws DaoException {
        employeeDao.create(target);
    }

    public void delete(Employee target) throws DaoException {
        employeeDao.delete(target);
    }

    public Employee getById(int id) throws DaoException {
        return employeeDao.getById(id);
    }

    public List<Employee> getAll() throws DaoException {
        return employeeDao.getAll();
    }

    public Collection<Job> getAllJobs(Employee target) throws DaoException {
        return target.getJobs();
    }
}
