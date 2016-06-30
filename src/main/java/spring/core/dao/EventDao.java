package spring.core.dao;

import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Event;

import java.util.List;

public interface EventDao {
    void create(Event target) throws DaoException;

    void update(Event target) throws DaoException;

    void delete(int id) throws DaoException;

    Event getById(int id) throws DaoException;

    List<Event> getAllEvents() throws DaoException;
}
