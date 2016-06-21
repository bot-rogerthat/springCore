package spring.core.service;

import spring.core.dao.AuditoriumDao;
import spring.core.entity.Auditorium;

import java.util.List;
import java.util.Set;

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

    public Set<String> getVipSeats(String name) {
        return auditoriumDao.getAllAuditoriums().stream()
                .filter(auditorium -> name.equalsIgnoreCase(auditorium.getName()))
                .findFirst()
                .get()
                .getVips();
    }

    public AuditoriumDao getAuditoriumDao() {
        return auditoriumDao;
    }

    public void setAuditoriumDao(AuditoriumDao auditoriumDao) {
        this.auditoriumDao = auditoriumDao;
    }
}
