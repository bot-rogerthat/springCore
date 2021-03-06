package spring.core.service;

import spring.core.dao.UserDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Ticket;
import spring.core.entity.User;

import java.util.List;

public class UserService {
    private UserDao userDao;

    public void delete(int id) throws DaoException {
        userDao.delete(id);
    }

    public User getById(int id) throws DaoException {
        return userDao.getById(id);
    }

    public User getUserByEmail(String email) throws DaoException {
        List<User> users = userDao.getAllUsers();
        return users.stream()
                .filter(user -> email.equalsIgnoreCase(user.getEmail()))
                .findFirst()
                .get();
    }

    public User getUsersByName(String name) throws DaoException {
        List<User> users = userDao.getAllUsers();
        return users.stream()
                .filter(user -> name.equalsIgnoreCase(user.getName()))
                .findFirst()
                .get();
    }

    public List<Ticket> getBookedTickets(User target) throws DaoException {
        List<User> users = userDao.getAllUsers();
        return users.stream()
                .filter(user -> user.equals(target))
                .findFirst()
                .get()
                .getTickets();
    }

    public void create(User target) throws DaoException {
        userDao.create(target);
    }

    public List<User> getAllUsers() throws DaoException {
        return userDao.getAllUsers();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
