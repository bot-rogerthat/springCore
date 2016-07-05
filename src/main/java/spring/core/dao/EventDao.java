package spring.core.dao;

import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Event;

import java.util.List;

public interface EventDao {
    void create(Event target) throws DaoException;

    void update(Event target) throws DaoException;

    void delete(Event target) throws DaoException;

    Event getById(int id) throws DaoException;

    List<Event> getAll() throws DaoException;
}
