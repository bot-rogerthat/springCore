package spring.core.service;

import spring.core.dao.UserDao;
import spring.core.entity.Ticket;
import spring.core.entity.User;

import java.util.List;

public class UserService {
    private UserDao userDao;

    public void register(User target) {
        userDao.create(target);
    }

    public void remove(User target) {
        userDao.delete(target);
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public User getUserByEmail(String email) {
        List<User> users = userDao.getAllUsers();
        return users.stream()
                .filter(user -> email.equalsIgnoreCase(user.getEmail()))
                .findFirst()
                .get();
    }

    public User getUsersByName(String name) {
        List<User> users = userDao.getAllUsers();
        return users.stream()
                .filter(user -> name.equalsIgnoreCase(user.getName()))
                .findFirst()
                .get();
    }

    public List<Ticket> getBookedTickets(String name) {
        List<User> users = userDao.getAllUsers();
        return users.stream()
                .filter(user -> name.equalsIgnoreCase(user.getName()))
                .findFirst()
                .get()
                .getTickets();
    }

    public void create(User target) {
        userDao.create(target);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
