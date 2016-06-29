package spring.core.service;

import spring.core.dao.AuditoriumDao;
import spring.core.entity.Auditorium;

import java.util.List;

public class AuditoriumService {
    private AuditoriumDao auditoriumDao;

    public int getSeatsNumber(String name) {
        return auditoriumDao.getAllAuditoriums().stream()
                .filter(auditorium -> name.equalsIgnoreCase(auditorium.getName()))
                .findFirst()
                .get()
                .getNumberOfSeats();
    }

    public List<Integer> getVipSeats(int id) {
        return auditoriumDao.getAllAuditoriums().stream()
                .filter(auditorium -> id == auditorium.getId())
                .findFirst()
                .get()
                .getVips();
    }

    public void create(Auditorium target) {
        auditoriumDao.create(target);
    }

    public void delete(int id) {
        auditoriumDao.delete(id);
    }

    public List<Auditorium> getAllAuditoriums() {
        return auditoriumDao.getAllAuditoriums();
    }


    public void setAuditoriumDao(AuditoriumDao auditoriumDao) {
        this.auditoriumDao = auditoriumDao;
    }
}
