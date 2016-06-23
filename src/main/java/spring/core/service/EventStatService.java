package spring.core.service;

import spring.core.dao.EventStatDao;
import spring.core.entity.Event;
import spring.core.entity.EventStat;

public class EventStatService {
    private EventStatDao eventStatDao;

    public EventStat getEventStat(Event target) {
        EventStat eventStat = getEventStatByEvent(target);
        if (eventStat == null) {
            eventStat = new EventStat();
            eventStat.setEvent(target);
            eventStatDao.create(eventStat);
        }
        return getEventStatByEvent(target);
    }

    public void updateStat(EventStat target) {
        eventStatDao.update(target);
    }


    private EventStat getEventStatByEvent(Event target) {
        EventStat eventStat = eventStatDao.getAllEventStats().stream()
                .filter(es -> es.getEvent().getId() == target.getId())
                .findFirst()
                .orElse(null);
        return eventStat;
    }

    public EventStatDao getEventStatDao() {
        return eventStatDao;
    }

    public void setEventStatDao(EventStatDao eventStatDao) {
        this.eventStatDao = eventStatDao;
    }
}
