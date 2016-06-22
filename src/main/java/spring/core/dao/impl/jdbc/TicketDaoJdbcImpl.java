package spring.core.dao.impl.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import spring.core.dao.EventDao;
import spring.core.dao.TicketDao;
import spring.core.dao.UserDao;
import spring.core.entity.Ticket;

import java.util.List;

public class TicketDaoJdbcImpl implements TicketDao {
    private JdbcTemplate jdbcTemplate;
    private EventDao eventDao;
    private UserDao userDao;

    @Override
    public void create(Ticket target) {
        jdbcTemplate.update("INSERT INTO ticket(seat, is_booked, event_id, user_id) VALUES(?,?,?,?)",
                target.getSeat(),
                target.isBooked(),
                target.getEvent().getId(),
                target.getUser().getId());
    }

    @Override
    public void update(Ticket target) {
        jdbcTemplate.update("UPDATE ticket SET seat=?, is_booked=?, event_id=?, user_id=? WHERE id=?",
                target.getSeat(),
                target.isBooked(),
                target.getEvent().getId(),
                target.getUser().getId(),
                target.getId());
    }

    @Override
    public void delete(Ticket target) {
        jdbcTemplate.update("DELETE FROM ticket WHERE ID=?", target.getId());
    }

    @Override
    public Ticket getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ticket WHERE id=?", new Object[]{id},
                getTicketRowMapper());
    }

    @Override
    public List<Ticket> getAllTickets() {
        return jdbcTemplate.query("SELECT * FROM ticket", getTicketRowMapper());
    }

    private RowMapper<Ticket> getTicketRowMapper() {
        return (rs, i) -> {
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.setSeat(rs.getInt("seat"));
            ticket.setBooked(rs.getBoolean("is_booked"));
            ticket.setEvent(eventDao.getById(rs.getInt("event_id")));
            ticket.setUser(userDao.getById(rs.getInt("user_id")));
            return ticket;
        };
    }

    public EventDao getEventDao() {
        return eventDao;
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
