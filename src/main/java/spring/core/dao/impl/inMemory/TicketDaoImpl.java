package spring.core.dao.impl.inmemory;

import spring.core.dao.TicketDao;
import spring.core.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {
    private List<Ticket> tickets = new ArrayList<>();

    public void create(Ticket target) {
        tickets.add(target);
    }

    public void update(Ticket target) {
        if (!tickets.add(target)) {
            tickets.remove(target);
            tickets.add(target);
        }
    }

    public void delete(int id) {
        tickets.remove(tickets.stream()
                .filter(ticket -> ticket.getId() == id)
                .findFirst()
                .get());
    }

    public Ticket getById(int id) {
        return tickets.stream()
                .filter(ticket -> ticket.getId() == id)
                .findFirst()
                .get();
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "TicketDaoImpl{" +
                "tickets=" + tickets +
                '}';
    }
}
