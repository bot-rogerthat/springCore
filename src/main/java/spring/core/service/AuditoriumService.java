package spring.core.service;

import spring.core.dao.AuditoriumDao;
import spring.core.entity.Auditorium;

import java.util.List;

public class AuditoriumService {
    private AuditoriumDao auditoriumDao;

    public List<Auditorium> getAuditoriums() {
        return auditoriumDao.getAllAuditoriums();
    }

    public int getSeatsNumber(String name) {
        return auditoriumDao.getAllAuditoriums().stream()
                .filter(auditorium -> name.equalsIgnoreCase(auditorium.getName()))
                .findFirst()
                .get()
                .getNumberOfSeats();
    }

    public List<Integer> getVipSeats(String name) {
        return auditoriumDao.getAllAuditoriums().stream()
                .filter(auditorium -> name.equalsIgnoreCase(auditorium.getName()))
                .findFirst()
                .get()
                .getVips();
    }

    public void create(Auditorium target) {
        auditoriumDao.create(target);
    }

    public List<Auditorium> getAllAuditoriums() {
        return auditoriumDao.getAllAuditoriums();
    }


    public void setAuditoriumDao(AuditoriumDao auditoriumDao) {
        this.auditoriumDao = auditoriumDao;
    }
}
