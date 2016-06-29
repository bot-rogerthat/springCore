package spring.core.dao.impl.inMemory;

import spring.core.dao.UserDao;
import spring.core.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private List<User> users = new ArrayList<>();

    public void create(User target) {
        users.add(target);
    }

    public void update(User target) {
        if (!users.add(target)) {
            users.remove(target);
            users.add(target);
        }
    }

    public void delete(int id) {
        users.remove(users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .get());
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

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserDaoImpl{" +
                "users=" + users +
                '}';
    }
}
