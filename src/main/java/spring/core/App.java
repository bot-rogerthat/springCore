package spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.core.dao.EventDao;
import spring.core.dao.TicketDao;
import spring.core.dao.UserDao;
import spring.core.dao.impl.AuditoriumDaoImpl;
import spring.core.dao.impl.EventDaoImpl;
import spring.core.dao.impl.TicketDaoImpl;
import spring.core.dao.impl.UserDaoImpl;
import spring.core.entity.Auditorium;
import spring.core.entity.Event;
import spring.core.entity.Ticket;
import spring.core.entity.User;
import spring.core.entity.discount.DiscountStrategy;
import spring.core.entity.discount.impl.DiscountBirthday;
import spring.core.entity.discount.impl.DiscountEachTenTicket;
import spring.core.service.*;

public class App {
    public static void main(String[] args) {
        System.out.println("-------------------USER-------------------");
        ApplicationContext ctxUser = new ClassPathXmlApplicationContext("spring/user.xml");
        UserDao userDao = (UserDaoImpl) ctxUser.getBean("userDao");
        UserService userService = (UserService) ctxUser.getBean("userService");
        User user1 = (User) ctxUser.getBean("john");
        User user2 = (User) ctxUser.getBean("masha");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(userDao.getAllUsers());
        System.out.println(userService.getUserDao());

        System.out.println("-------------------AUDITORIUM-------------------");
        ApplicationContext ctxAuditor = new ClassPathXmlApplicationContext("spring/auditorium.xml");
        Auditorium auditor1 = (Auditorium) ctxAuditor.getBean("rightHall");
        Auditorium auditor2 = (Auditorium) ctxAuditor.getBean("leftHall");
        AuditoriumDaoImpl auditorDao = (AuditoriumDaoImpl) ctxAuditor.getBean("auditoriumDaoImpl");
        AuditoriumService auditoriumService = (AuditoriumService) ctxAuditor.getBean("auditoriumService");
        System.out.println(auditor1);
        System.out.println(auditor2);
        System.out.println(auditorDao.getAllAuditoriums());
        System.out.println(auditoriumService.getAuditoriumDao());

        System.out.println("-------------------DISCOUNT-------------------");
        ApplicationContext ctxDiscount = new ClassPathXmlApplicationContext("spring/discount.xml");
        DiscountStrategy discount1 = (DiscountBirthday) ctxDiscount.getBean("discountBirthday");
        DiscountStrategy discount2 = (DiscountEachTenTicket) ctxDiscount.getBean("discountEachTenTicket");
        DiscountService discounts = (DiscountService) ctxDiscount.getBean("discountService");
        System.out.println(discount1);
        System.out.println(discount2);
        System.out.println(discounts.getDiscounts());

        System.out.println("-------------------EVENT-------------------");
        ApplicationContext ctxEvent = new ClassPathXmlApplicationContext("spring/event.xml");
        Event event = (Event) ctxEvent.getBean("warcraft");
        EventDao eventDao = (EventDaoImpl) ctxEvent.getBean("eventDao");
        EventService eventService = (EventService) ctxEvent.getBean("eventService");
        System.out.println(event);
        System.out.println(eventDao.getAllEvents());
        System.out.println(eventService.getEventDao());

        System.out.println("-------------------BOOKING-------------------");
        ApplicationContext ctxBooking = new ClassPathXmlApplicationContext("spring/booking.xml", "spring/user.xml", "spring/discount.xml");
        Ticket ticket1 = (Ticket) ctxBooking.getBean("ticket1");
        Ticket ticket2 = (Ticket) ctxBooking.getBean("ticket2");
        TicketDao ticketDao = (TicketDaoImpl) ctxBooking.getBean("ticketDao");
        BookingService bookingService = (BookingService) ctxBooking.getBean("bookingService");
        System.out.println(ticket1);
        System.out.println(ticket2);
        System.out.println(ticketDao.getAllTickets());
        System.out.println(bookingService.getTicketDao());
        System.out.println(bookingService.getUserDao());

        System.out.println("-------------------TEST-------------------");
        eventService.assignAuditorium(event, auditor1, "2016-05-27 19:20");
        ticket1.setEvent(event);
        System.out.println("event: " + event);
        bookingService.bookTicket(user1, ticket1);
        System.out.println("auditorium: " + auditor1.getName());
        System.out.println("seats: " + auditoriumService.getSeatsNumber("right"));
        System.out.println("vips: " + auditoriumService.getVipSeats("right"));
        System.out.println("user: " + userService.getUserByEmail("john@mail.ru"));
        System.out.println("user ticket: " + userService.getBookedTickets("john").get(0));
        System.out.println("price for user: " + bookingService.getTicketPrice(event, "1", user1));
        System.out.println("all purchased tickets for event : " + bookingService.getTicketsForEvent(event));
    }
}
