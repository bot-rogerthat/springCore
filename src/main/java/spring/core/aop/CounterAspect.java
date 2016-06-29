package spring.core.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import spring.core.dao.EventDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Event;
import spring.core.entity.EventStat;
import spring.core.entity.Ticket;
import spring.core.service.EventStatService;

@Aspect
public class CounterAspect {
    private EventStatService eventStatService;
    private EventDao eventDao;

    @AfterReturning(pointcut = "execution(* spring.core.service.EventService.getByName(..))", returning = "event")
    public void addCountEventByName(Event event) throws DaoException {
        EventStat eventStat = eventStatService.getEventStat(event);
        eventStat.setCountByEventName(eventStat.getCountByEventName() + 1);
        eventStatService.updateStat(eventStat);
    }

    @AfterReturning("execution(* spring.core.service.BookingService.getTicketPrice(..)) && args(event, ..)")
    public void addCountEventGetTicketPrice(Event event) throws DaoException {
        EventStat eventStat = eventStatService.getEventStat(event);
        eventStat.setCountByEventPrice(eventStat.getCountByEventPrice() + 1);
        eventStatService.updateStat(eventStat);
    }

    @AfterReturning("execution(* spring.core.service.BookingService.bookTicket(..)) && args(.., ticket)")
    public void addCountTicketBooked(Ticket ticket) throws DaoException {
        EventStat eventStat = eventStatService.getEventStat(eventDao.getById(ticket.getEventId()));
        eventStat.setCountByTicketBooked(eventStat.getCountByTicketBooked() + 1);
        eventStatService.updateStat(eventStat);
    }

    public EventStatService getEventStatService() {
        return eventStatService;
    }

    public void setEventStatService(EventStatService eventStatService) {
        this.eventStatService = eventStatService;
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }
}
