package spring.core.dao.impl;

import spring.core.dao.EventDao;
import spring.core.entity.Event;

import java.util.ArrayList;
import java.util.List;

public class EventDaoImpl implements EventDao {
    private List<Event> events = new ArrayList<>();

    public void create(Event target) {
        events.add(target);
    }

    public void update(Event target) {
        /*NOP*/
    }

    public void delete(Event target) {
        if (events.contains(target)) {
            events.remove(target);
        }
    }

    public Event getById(String id) {
         /*NOP*/
        return null;
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "EventDaoImpl{" +
                "events=" + events +
                '}';
    }
}
