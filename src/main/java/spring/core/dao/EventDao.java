package spring.core.dao;

import spring.core.entity.Event;

import java.util.List;

public interface EventDao {
    void create(Event target);

    void update(Event target);

    void delete(Event target);

    Event getById(int id);

    List<Event> getAllEvents();
}
