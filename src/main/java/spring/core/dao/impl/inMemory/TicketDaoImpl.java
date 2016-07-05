package spring.core.dao.impl.inMemory;

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

    public void delete(Ticket target) {
        tickets.remove(target);
    }

    public Ticket getById(int id) {
        return tickets.stream()
                .filter(ticket -> ticket.getId() == id)
                .findFirst()
                .get();
    }

    public List<Ticket> getAll() {
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
