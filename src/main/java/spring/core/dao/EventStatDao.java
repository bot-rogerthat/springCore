package spring.core.dao;

import spring.core.entity.EventStat;

import java.util.List;

public interface EventStatDao {
    void create(EventStat target);

    void update(EventStat target);

    void delete(int id);

    EventStat getById(int id);

    List<EventStat> getAllEventStats();
}
