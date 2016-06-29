package spring.core.dao.impl.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import spring.core.dao.UserDao;
import spring.core.entity.Ticket;
import spring.core.entity.User;

import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(User target) throws DaoException{
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
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM user_ WHERE id=?", id);
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
            user.setTickets(getTicketsForUserById(rs.getInt("id")));
            return user;
        };
    }

    private List<Ticket> getTicketsForUserById(int id) {
        return jdbcTemplate.query("SELECT * FROM ticket WHERE user_id=?", new Object[]{id},
                getTicketRowMapper());
    }

    private RowMapper<Ticket> getTicketRowMapper() {
        return (rs, i) -> {
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.setSeat(rs.getInt("seat"));
            ticket.setBooked(rs.getBoolean("is_booked"));
            ticket.setEventId(rs.getInt("event_id"));
            ticket.setUserId(rs.getInt("user_id"));
            return ticket;
        };
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
