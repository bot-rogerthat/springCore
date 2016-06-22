package spring.core.dao;

import spring.core.entity.Auditorium;

import java.util.List;

public interface AuditoriumDao {
    void create(Auditorium target);

    void update(Auditorium target);

    void delete(Auditorium target);

    Auditorium getById(int id);

    List<Auditorium> getAllAuditoriums();
}
