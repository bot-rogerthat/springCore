package spring.core.service;

import spring.core.dao.EventDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Auditorium;
import spring.core.entity.Event;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class EventService {
    private EventDao eventDao;

    public void create(Event target) throws DaoException {
        eventDao.create(target);
    }

    public void delete(int id) throws DaoException{
        eventDao.delete(id);
    }

    public Event getByName(String name) throws DaoException{
        List<Event> events = eventDao.getAllEvents();
        return events.stream()
                .filter(event -> name.equalsIgnoreCase(event.getName()))
                .findFirst()
                .get();
    }

    public List<Event> getAll() throws DaoException{
        return eventDao.getAllEvents();
    }

    public Event getById(int id) throws DaoException{
        return eventDao.getById(id);
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Timestamp date, Time time) throws DaoException{
        event.setAuditoriumId(auditorium.getId());
        event.setDate(date);
        event.setTime(time);
        eventDao.update(event);
    }

    public List<Event> getAllEvents() throws DaoException{
        return eventDao.getAllEvents();
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }
}
