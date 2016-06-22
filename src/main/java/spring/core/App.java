package spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.core.entity.Auditorium;
import spring.core.entity.Event;
import spring.core.entity.Ticket;
import spring.core.entity.User;
import spring.core.service.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class App {
    private AuditoriumService auditoriumService;
    private BookingService bookingService;
    private DiscountService discountService;
    private EventService eventService;
    private UserService userService;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/main.xml");
        App app = ctx.getBean("app", App.class);

        User user = app.getUserService().getUsersByName("john");
        Event event = app.getEventService().getEventDao().getAllEvents().get(0);
        Auditorium auditorium = app.getAuditoriumService().getAuditoriumDao().getAllAuditoriums().get(0);
        Ticket ticket = app.getBookingService().getTicketDao().getAllTickets().get(0);

        app.getEventService().assignAuditorium(event, auditorium, LocalDate.parse("2016-05-27"), LocalTime.parse("19:20"));
        ticket.setEvent(event);
        app.getBookingService().bookTicket(user, ticket);

        System.out.println("event: " + event);
        System.out.println("auditorium: " + auditorium.getName());
        System.out.println("seats: " + app.getAuditoriumService().getSeatsNumber("rightHall"));
        System.out.println("vips: " + app.getAuditoriumService().getVipSeats("rightHall"));
        System.out.println("user: " + app.getUserService().getUserByEmail("john@mail.ru"));
        System.out.println("user ticket: " + app.getUserService().getBookedTickets("john").get(0));
        System.out.println("price for user (discount birthday): " + app.getBookingService().getTicketPrice(event, "1", user));
        System.out.println("all purchased tickets for event : " + app.getBookingService().getTicketsForEvent(event));
        for (int i = 0; i < 8; i++) {
            Ticket newTicket = new Ticket("" + i);
            newTicket.setEvent(event);
            app.getBookingService().bookTicket(user, newTicket);
        }
        System.out.println("price for user (discount each ten ticket): " + app.getBookingService().getTicketPrice(event, "1", user));
    }


    public AuditoriumService getAuditoriumService() {
        return auditoriumService;
    }

    public void setAuditoriumService(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    public BookingService getBookingService() {
        return bookingService;
    }

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public DiscountService getDiscountService() {
        return discountService;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public EventService getEventService() {
        return eventService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
