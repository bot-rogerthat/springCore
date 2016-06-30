package spring.core.service;

import spring.core.dao.TicketDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Event;
import spring.core.entity.Ticket;
import spring.core.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {

    private final BigDecimal VIP_MARKUP = new BigDecimal(50);
    private TicketDao ticketDao;
    private DiscountService discountService;
    private AuditoriumService auditoriumService;

    public BigDecimal getTicketPrice(Event event, Integer seat, User user) throws DaoException {
        BigDecimal discount = discountService.getDiscount(user, event);
        boolean isVip = auditoriumService.getVipSeats(event.getAuditoriumId()).contains(seat);
        BigDecimal price = event.getPrice().add(isVip ? VIP_MARKUP : BigDecimal.ZERO);
        return price.multiply(discount);
    }

    public void bookTicket(User user, Ticket ticket) {
        ticket.setBooked(true);
        ticket.setUserId(user.getId());
        user.getTickets().add(ticket);
    }

    public List<Ticket> getTicketsForEvent(Event event) throws DaoException {
        return ticketDao.getAllTickets().stream()
                .filter(ticket -> event.getId() == ticket.getEventId())
                .filter(Ticket::isBooked)
                .collect(Collectors.toList());
    }

    public void create(Ticket target) throws DaoException {
        ticketDao.create(target);
    }

    public List<Ticket> getAllTickets() throws DaoException {
        return ticketDao.getAllTickets();
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }
}
