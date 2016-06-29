package spring.core.service;

import spring.core.dao.EventStatDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Event;
import spring.core.entity.EventStat;

import java.util.List;

public class EventStatService {
    private EventStatDao eventStatDao;

    public EventStat getEventStat(Event target) throws DaoException {
        EventStat eventStat = getEventStatByEvent(target);
        if (eventStat == null) {
            eventStat = new EventStat();
            eventStat.setEventId(target.getId());
            eventStatDao.create(eventStat);
        }
        return getEventStatByEvent(target);
    }

    public void updateStat(EventStat target) throws DaoException {
        eventStatDao.update(target);
    }


    private EventStat getEventStatByEvent(Event target) throws DaoException{
        return eventStatDao.getAllEventStats().stream()
                .filter(es -> es.getEventId() == target.getId())
                .findFirst()
                .orElse(null);
    }

    public List<EventStat> getAllEventStats() throws DaoException {
        return eventStatDao.getAllEventStats();
    }

    public void setEventStatDao(EventStatDao eventStatDao) {
        this.eventStatDao = eventStatDao;
    }
}
