package spring.core.dao.impl.inmemory;

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
        if (!events.add(target)) {
            events.remove(target);
            events.add(target);
        }
    }

    public void delete(int id) {
        events.remove(events.stream()
                .filter(event -> event.getId() == id)
                .findFirst()
                .get());
    }

    public Event getById(int id) {
        return events.stream()
                .filter(event -> event.getId() == id)
                .findFirst()
                .get();
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
