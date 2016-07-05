package spring.core.dao;

import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Auditorium;

import java.util.List;

public interface AuditoriumDao {
    void create(Auditorium target) throws DaoException;

    void update(Auditorium target) throws DaoException;

    void delete(Auditorium target) throws DaoException;

    Auditorium getById(int id) throws DaoException;

    List<Auditorium> getAll() throws DaoException;
}
