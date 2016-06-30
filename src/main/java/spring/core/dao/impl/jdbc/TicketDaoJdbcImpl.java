package spring.core.dao.impl.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import spring.core.dao.TicketDao;
import spring.core.entity.Ticket;

import java.util.List;

public class TicketDaoJdbcImpl implements TicketDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Ticket target) {
        jdbcTemplate.update("INSERT INTO ticket(seat, is_booked, event_id, user_id) VALUES(?,?,?,?)",
                target.getSeat(),
                target.isBooked(),
                target.getEventId(),
                target.getUserId());
    }

    @Override
    public void update(Ticket target) {
        jdbcTemplate.update("UPDATE ticket SET seat=?, is_booked=?, event_id=?, user_id=? WHERE id=?",
                target.getSeat(),
                target.isBooked(),
                target.getEventId(),
                target.getUserId(),
                target.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM ticket WHERE id=?", id);
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
            ticket.setEventId((rs.getInt("event_id")));
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
