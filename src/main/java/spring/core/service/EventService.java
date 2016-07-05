package spring.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.core.dao.EventDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Auditorium;
import spring.core.entity.Event;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    public void create(Event target) throws DaoException {
        eventDao.create(target);
    }

    public void delete(Event target) throws DaoException {
        eventDao.delete(target);
    }

    public Event getByName(String name) throws DaoException {
        List<Event> events = eventDao.getAll();
        return events.stream()
                .filter(event -> name.equalsIgnoreCase(event.getName()))
                .findFirst()
                .get();
    }

    public List<Event> getAll() throws DaoException {
        return eventDao.getAll();
    }

    public Event getById(int id) throws DaoException {
        return eventDao.getById(id);
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Timestamp date, Time time) throws DaoException {
        event.setAuditoriumId(auditorium.getId());
        event.setDate(date);
        event.setTime(time);
        eventDao.update(event);
    }

    public List<Event> getAllEvents() throws DaoException {
        return eventDao.getAll();
    }
}
