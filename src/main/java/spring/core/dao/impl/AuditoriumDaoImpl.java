package spring.core.dao.impl;

import spring.core.dao.AuditoriumDao;
import spring.core.entity.Auditorium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuditoriumDaoImpl implements AuditoriumDao {
    private Set<Auditorium> auditoriums = new HashSet<>();

    public void create(Auditorium target) {
        auditoriums.add(target);
    }

    public void update(Auditorium target) {
        /*NOP*/
    }

    public void delete(Auditorium target) {
        if (auditoriums.contains(target)) {
            auditoriums.remove(target);
        }
    }

    public Auditorium getById(String id) {
         /*NOP*/
        return null;
    }

    public List<Auditorium> getAllAuditoriums() {
        return new ArrayList<>(auditoriums);
    }

    public void setAuditoriums(Set<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    @Override
    public String toString() {
        return "AuditoriumDaoImpl{" +
                "auditoriums=" + auditoriums +
                '}';
    }
}