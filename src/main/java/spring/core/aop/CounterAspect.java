package spring.core.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import spring.core.entity.Event;
import spring.core.entity.Ticket;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class CounterAspect {
    private Map<String, Integer> counterByEventName = new HashMap<>();
    private Map<String, Integer> counterByEventPrice = new HashMap<>();
    private Map<String, Integer> counterByTicketBooked = new HashMap<>();

    @AfterReturning("execution(* spring.core.service.EventService.getByName(..)) && args(eventName)")
    public void addCountEventByName(String eventName) {
        int count = counterByEventName.getOrDefault(eventName, 0);
        counterByEventName.put(eventName, ++count);
        System.out.println("counterByEventName " + counterByEventName);
    }

    @AfterReturning("execution(* spring.core.service.BookingService.getTicketPrice(..)) && args(event, ..)")
    public void addCountEventGetTicketPrice(Event event) {
        int count = counterByEventPrice.getOrDefault(event.getName(), 0);
        counterByEventPrice.put(event.getName(), ++count);
        System.out.println("counterByEventPrice " + counterByEventPrice);
    }

    @AfterReturning("execution(* spring.core.service.BookingService.bookTicket(..)) && args(.., ticket)")
    public void addCountTicketBooked(Ticket ticket) {
        int count = counterByTicketBooked.getOrDefault(ticket.getEvent().getName(), 0);
        counterByTicketBooked.put(ticket.getEvent().getName(), ++count);
        System.out.println("counterByTicketBooked " + counterByTicketBooked);
    }
}
