package spring.core.dao.impl.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import spring.core.dao.UserDao;
import spring.core.entity.User;

import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(User target) {
        jdbcTemplate.update("INSERT INTO user_ (name, email, day_of_birth) VALUES(?,?,?)",
                target.getName(),
                target.getEmail(),
                target.getDayOfBirth());
    }

    @Override
    public void update(User target) {
        jdbcTemplate.update("UPDATE user_ SET name=?, email=?, day_of_birth=? WHERE id=?",
                target.getName(),
                target.getEmail(),
                target.getDayOfBirth(),
                target.getId());
        if (target.getTickets() != null) {
            target.getTickets().forEach(ticket ->
                    jdbcTemplate.update("UPDATE ticket SET user_id=? WHERE id=?", target.getId(), ticket.getId()));
        }
    }

    @Override
    public void delete(User target) {
        jdbcTemplate.update("DELETE FROM user_ WHERE ID=?", target.getId());
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM user_ WHERE id=?", new Object[]{id},
                getUserRowMapper());
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM user_", getUserRowMapper());
    }

    private RowMapper<User> getUserRowMapper() {
        return (rs, i) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setDayOfBirth(rs.getTimestamp("day_of_birth"));
            return user;
        };
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
