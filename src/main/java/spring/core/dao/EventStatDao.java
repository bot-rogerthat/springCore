package spring.core.dao;

import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.EventStat;

import java.util.List;

public interface EventStatDao {
    void create(EventStat target) throws DaoException;

    void update(EventStat target) throws DaoException;

    void delete(EventStat target) throws DaoException;

    EventStat getById(int id) throws DaoException;

    List<EventStat> getAll() throws DaoException;
}
