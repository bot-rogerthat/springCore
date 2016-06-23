package spring.core.dao.impl.inmemory;

import spring.core.dao.AuditoriumDao;
import spring.core.entity.Auditorium;

import java.util.ArrayList;
import java.util.List;

public class AuditoriumDaoImpl implements AuditoriumDao {
    private List<Auditorium> auditoriums = new ArrayList<>();

    public void create(Auditorium target) {
        auditoriums.add(target);
    }

    public void update(Auditorium target) {
        if (!auditoriums.add(target)) {
            auditoriums.remove(target);
            auditoriums.add(target);
        }
    }

    public void delete(Auditorium target) {
        if (auditoriums.contains(target)) {
            auditoriums.remove(target);
        }
    }

    public Auditorium getById(int id) {
        return auditoriums.stream()
                .filter(auditorium -> auditorium.getId() == id)
                .findFirst()
                .get();
    }

    public List<Auditorium> getAllAuditoriums() {
        return new ArrayList<>(auditoriums);
    }

    public void setAuditoriums(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    @Override
    public String toString() {
        return "AuditoriumDaoImpl{" +
                "auditoriums=" + auditoriums +
                '}';
    }
}
