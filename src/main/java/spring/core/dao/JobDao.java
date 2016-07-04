package spring.core.dao;

import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Job;

import java.util.List;

public interface JobDao {
    void create(Job target) throws DaoException;

    void update(Job target) throws DaoException;

    void delete(Job target) throws DaoException;

    Job getById(int id) throws DaoException;

    List<Job> getAll() throws DaoException;
}
