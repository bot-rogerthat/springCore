package spring.core.dao;

import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Ticket;

import java.util.List;

public interface TicketDao {
    void create(Ticket target) throws DaoException;

    void update(Ticket target) throws DaoException;

    void delete(int id) throws DaoException;

    Ticket getById(int id) throws DaoException;

    List<Ticket> getAllTickets() throws DaoException;
}
