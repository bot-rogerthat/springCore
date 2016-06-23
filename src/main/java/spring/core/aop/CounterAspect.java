package spring.core.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import spring.core.entity.Event;
import spring.core.entity.EventStat;
import spring.core.entity.Ticket;
import spring.core.service.EventStatService;

@Aspect
public class CounterAspect {
    private EventStatService eventStatService;

    @AfterReturning(pointcut = "execution(* spring.core.service.EventService.getByName(..))", returning = "event")
    public void addCountEventByName(Event event) {
        EventStat eventStat = eventStatService.getEventStat(event);
        eventStat.setCountByEventName(eventStat.getCountByEventName() + 1);
        eventStatService.updateStat(eventStat);
    }

    @AfterReturning("execution(* spring.core.service.BookingService.getTicketPrice(..)) && args(event, ..)")
    public void addCountEventGetTicketPrice(Event event) {
        EventStat eventStat = eventStatService.getEventStat(event);
        eventStat.setCountByEventPrice(eventStat.getCountByEventPrice() + 1);
        eventStatService.updateStat(eventStat);
    }

    @AfterReturning("execution(* spring.core.service.BookingService.bookTicket(..)) && args(.., ticket)")
    public void addCountTicketBooked(Ticket ticket) {
        EventStat eventStat = eventStatService.getEventStat(ticket.getEvent());
        eventStat.setCountByTicketBooked(eventStat.getCountByTicketBooked() + 1);
        eventStatService.updateStat(eventStat);
    }

    public EventStatService getEventStatService() {
        return eventStatService;
    }

    public void setEventStatService(EventStatService eventStatService) {
        this.eventStatService = eventStatService;
    }
}
