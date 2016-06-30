package spring.core.dao.impl.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import spring.core.dao.AuditoriumDao;
import spring.core.dao.EventDao;
import spring.core.entity.Event;
import spring.core.entity.Rating;

import java.util.List;

public class EventDaoJdbcImpl implements EventDao {
    private JdbcTemplate jdbcTemplate;
    private AuditoriumDao auditoriumDao;

    @Override
    public void create(Event target) {
        jdbcTemplate.update("INSERT INTO event(name, date, time, price, rating, auditorium_id) VALUES(?,?,?,?,?,?)",
                target.getName(),
                target.getDate(),
                target.getTime(),
                target.getPrice(),
                target.getRating().toString(),
                target.getAuditoriumId());
    }

    @Override
    public void update(Event target) {
        jdbcTemplate.update("UPDATE event SET name=?, date=?, time=?, price=?, rating=?, auditorium_id=? WHERE id=?",
                target.getName(),
                target.getDate(),
                target.getTime(),
                target.getPrice(),
                target.getRating().toString(),
                target.getAuditoriumId(),
                target.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM event WHERE id=?", id);
    }

    @Override
    public Event getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM event WHERE id=?", new Object[]{id},
                getEventRowMapper());
    }

    @Override
    public List<Event> getAllEvents() {
        return jdbcTemplate.query("SELECT * FROM event", getEventRowMapper());
    }

    private RowMapper<Event> getEventRowMapper() {
        return (rs, i) -> {
            Event event = new Event();
            event.setId(rs.getInt("id"));
            event.setName(rs.getString("name"));
            event.setDate(rs.getTimestamp("date"));
            event.setTime(rs.getTime("time"));
            event.setPrice(rs.getBigDecimal("price"));
            event.setRating(Rating.valueOf(rs.getString("rating")));
            event.setAuditoriumId(rs.getInt("auditorium_id"));
            return event;
        };
    }

    public AuditoriumDao getAuditoriumDao() {
        return auditoriumDao;
    }

    public void setAuditoriumDao(AuditoriumDao auditoriumDao) {
        this.auditoriumDao = auditoriumDao;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}