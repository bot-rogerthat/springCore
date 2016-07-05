package spring.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.core.dao.AuditoriumDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Auditorium;

import java.util.List;

@Service
public class AuditoriumService {

    @Autowired
    private AuditoriumDao auditoriumDao;

    public int getSeatsNumber(String name) throws DaoException {
        return auditoriumDao.getAll().stream()
                .filter(auditorium -> name.equalsIgnoreCase(auditorium.getName()))
                .findFirst()
                .get()
                .getNumberOfSeats();
    }

    public List<Integer> getVipSeats(int id) throws DaoException {
        return auditoriumDao.getAll().stream()
                .filter(auditorium -> id == auditorium.getId())
                .findFirst()
                .get()
                .getVips();
    }

    public void create(Auditorium target) throws DaoException {
        auditoriumDao.create(target);
    }

    public void delete(Auditorium target) throws DaoException {
        auditoriumDao.delete(target);
    }

    public Auditorium getById(int id) throws DaoException {
        return auditoriumDao.getById(id);
    }

    public List<Auditorium> getAllAuditoriums() throws DaoException {
        return auditoriumDao.getAll();
    }
}
