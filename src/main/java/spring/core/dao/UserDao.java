package spring.core.dao;

import spring.core.entity.User;

import java.util.List;

public interface UserDao {
    void create(User target);

    void update(User target);

    void delete(User target);

    User getById(int id);

    List<User> getAllUsers();
}
