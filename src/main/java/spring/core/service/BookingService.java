package spring.core.service;

import spring.core.dao.TicketDao;
import spring.core.dao.UserDao;
import spring.core.entity.Event;
import spring.core.entity.Ticket;
import spring.core.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class BookingService {

    private final int VIP_MARKUP = 50;
    private TicketDao ticketDao;
    private UserDao userDao;
    private DiscountService discountService;

    public double getTicketPrice(Event event, String seat, User user) {
        double discount = discountService.getDiscount(user, event);
        double price = ticketDao.getAllTickets().stream()
                .filter(ticket -> event.equals(ticket.getEvent()))
                .filter(ticket -> seat.equals(ticket.getSeat()))
                .findFirst()
                .get()
                .getEvent().getPrice() + (event.getAuditorium().isVip(seat) ? VIP_MARKUP : 0);
        return price * discount;
    }

    public void bookTicket(User user, Ticket ticket) {
        ticket.setBooked(true);
        ticket.setUser(user);
        user.addTicket(ticket);
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
