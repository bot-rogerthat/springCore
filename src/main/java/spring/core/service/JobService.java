package spring.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.core.dao.JobDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Employee;
import spring.core.entity.Job;

import java.sql.Timestamp;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobDao jobDao;

    public void create(Job target) throws DaoException {
        jobDao.create(target);
    }

    public void delete(Job target) throws DaoException {
        jobDao.delete(target);
    }

    public Job getById(int id) throws DaoException {
        return jobDao.getById(id);
    }

    public List<Job> getAll() throws DaoException {
        return jobDao.getAll();
    }

    public void assign(Employee employee, Job job, Timestamp date) throws DaoException {
        job.setDate(date);
        job.setEmployee(employee);
        jobDao.update(job);
    }
}
