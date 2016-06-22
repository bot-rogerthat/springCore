package spring.core.dao.impl;

import spring.core.dao.UserDao;
import spring.core.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private Set<User> users = new HashSet<>();

    public void create(User target) {
        users.add(target);
    }

    public void update(User target) {
        if (!users.add(target)) {
            users.remove(target);
            users.add(target);
        }
    }

    public void delete(User target) {
        if (users.contains(target)) {
            users.remove(target);
        }
    }

    public User getById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .get();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserDaoImpl{" +
                "users=" + users +
                '}';
    }
}
