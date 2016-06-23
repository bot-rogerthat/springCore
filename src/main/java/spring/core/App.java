package spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.core.entity.*;
import spring.core.service.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;

public class App {
    private AuditoriumService auditoriumService;
    private BookingService bookingService;
    private DiscountService discountService;
    private EventService eventService;
    private EventStatService eventStatService;
    private UserService userService;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/main.xml");
        App app = ctx.getBean("app", App.class);

        Auditorium auditorium = new Auditorium();
        auditorium.setName("gr45Hall");
        auditorium.setNumberOfSeats(50);
        auditorium.setVips(Arrays.asList(1, 2, 3, 4));

        app.getAuditoriumService().getAuditoriumDao().create(auditorium);

        User user = new User();
        user.setName("vasya");
        user.setEmail("vasya@mail.ru");
        user.setDayOfBirth(Timestamp.valueOf("2016-05-27 00:00:00"));

        app.getUserService().getUserDao().create(user);

        Event event = new Event();
        event.setName("warcraft");
        event.setDate(Timestamp.valueOf("2016-05-27 00:00:00"));
        event.setTime(Time.valueOf("19:20:00"));
        event.setPrice(new BigDecimal(300));
        event.setRating(Rating.MID);
        event.setAuditorium(app.getAuditoriumService().getAuditoriumDao().getAllAuditoriums().get(0));

        app.getEventService().getEventDao().create(event);

        Ticket ticket = new Ticket();
        ticket.setSeat(25);
        ticket.setEvent(app.getEventService().getEventDao().getAllEvents().get(0));
        ticket.setBooked(true);
        ticket.setUser(app.getUserService().getUserDao().getAllUsers().get(0));

        app.getBookingService().getTicketDao().create(ticket);

        //counters++
        BigDecimal price = app.getBookingService().getTicketPrice(app.getEventService().getEventDao().getAllEvents().get(0), 25,
                app.getUserService().getUserDao().getAllUsers().get(0));
        app.getBookingService().bookTicket(user, ticket);
        app.getEventService().getByName("warcraft");

        System.out.println(app.getAuditoriumService().getAuditoriumDao().getAllAuditoriums());
        System.out.println(app.getUserService().getUserDao().getAllUsers());
        System.out.println(app.getEventService().getEventDao().getAllEvents());
        System.out.println(app.getBookingService().getTicketDao().getAllTickets());
        System.out.println(app.getEventStatService().getEventStatDao().getAllEventStats());
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

    public EventStatService getEventStatService() {
        return eventStatService;
    }

    public void setEventStatService(EventStatService eventStatService) {
        this.eventStatService = eventStatService;
    }
}
