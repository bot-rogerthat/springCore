package spring.core.service;

import spring.core.dao.EventDao;
import spring.core.entity.Auditorium;
import spring.core.entity.Event;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class EventService {
    private EventDao eventDao;

    public void create(Event target) {
        eventDao.create(target);
    }

    public void remove(Event target) {
        eventDao.delete(target);
    }

    public Event getByName(String name) {
        List<Event> events = eventDao.getAllEvents();
        return events.stream()
                .filter(event -> name.equalsIgnoreCase(event.getName()))
                .findFirst()
                .get();
    }

    public List<Event> getAll() {
        return eventDao.getAllEvents();
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Timestamp date, Time time) {
        event.setAuditorium(auditorium);
        event.setDate(date);
        event.setTime(time);
    }

    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }
}
