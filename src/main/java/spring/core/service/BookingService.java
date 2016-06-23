package spring.core.service;

import spring.core.dao.TicketDao;
import spring.core.dao.UserDao;
import spring.core.entity.Event;
import spring.core.entity.Ticket;
import spring.core.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {

    private final BigDecimal VIP_MARKUP = new BigDecimal(50);
    private TicketDao ticketDao;
    private UserDao userDao;
    private DiscountService discountService;

    public BigDecimal getTicketPrice(Event event, Integer seat, User user) {
        BigDecimal discount = discountService.getDiscount(user, event);
        BigDecimal price = ticketDao.getAllTickets().stream()
                .filter(ticket -> event.equals(ticket.getEvent()))
                .filter(ticket -> seat.equals(ticket.getSeat()))
                .findFirst()
                .get()
                .getEvent().getPrice().add((event.getAuditorium().getVips().contains(seat) ? VIP_MARKUP : BigDecimal.ZERO));
        return price.multiply(discount);
    }

    public void bookTicket(User user, Ticket ticket) {
        ticket.setBooked(true);
        ticket.setUser(user);
        user.getTickets().add(ticket);
    }

    public List<Ticket> getTicketsForEvent(Event event) {
        return ticketDao.getAllTickets().stream()
                .filter(ticket -> event.equals(ticket.getEvent()))
                .filter(Ticket::isBooked)
                .collect(Collectors.toList());
    }

    public TicketDao getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public DiscountService getDiscountService() {
        return discountService;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }
}
