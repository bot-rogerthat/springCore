package spring.core.dao;

import spring.core.entity.Ticket;

import java.util.List;

public interface TicketDao {
    void create(Ticket target);

    void update(Ticket target);

    void delete(int id);

    Ticket getById(int id);

    List<Ticket> getAllTickets();
}
