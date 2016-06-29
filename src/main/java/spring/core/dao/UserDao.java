package spring.core.dao;

import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.User;

import java.util.List;

public interface UserDao {
    void create(User target) throws DaoException;

    void update(User target) throws DaoException;

    void delete(int id) throws DaoException;

    User getById(int id) throws DaoException;

    List<User> getAllUsers() throws DaoException;
}
