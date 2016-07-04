package spring.core.dao;

import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    void create(Employee target) throws DaoException;

    void update(Employee target) throws DaoException;

    void delete(Employee target) throws DaoException;

    Employee getById(int id) throws DaoException;

    List<Employee> getAll() throws DaoException;
}
