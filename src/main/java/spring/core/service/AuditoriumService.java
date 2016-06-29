package spring.core.service;

import spring.core.dao.AuditoriumDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Auditorium;

import java.util.List;

public class AuditoriumService {
    private AuditoriumDao auditoriumDao;

    public int getSeatsNumber(String name) throws DaoException {
        return auditoriumDao.getAllAuditoriums().stream()
                .filter(auditorium -> name.equalsIgnoreCase(auditorium.getName()))
                .findFirst()
                .get()
                .getNumberOfSeats();
    }

    public List<Integer> getVipSeats(int id) throws DaoException {
        return auditoriumDao.getAllAuditoriums().stream()
                .filter(auditorium -> id == auditorium.getId())
                .findFirst()
                .get()
                .getVips();
    }

    public void create(Auditorium target) throws DaoException {
        auditoriumDao.create(target);
    }

    public void delete(int id) throws DaoException {
        auditoriumDao.delete(id);
    }

    public List<Auditorium> getAllAuditoriums() throws DaoException {
        return auditoriumDao.getAllAuditoriums();
    }


    public void setAuditoriumDao(AuditoriumDao auditoriumDao) {
        this.auditoriumDao = auditoriumDao;
    }
}
