package spring.core.dao.impl.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import spring.core.dao.EventDao;
import spring.core.dao.EventStatDao;
import spring.core.entity.EventStat;

import java.util.List;

public class EventStatDaoJdbcImpl implements EventStatDao {
    private JdbcTemplate jdbcTemplate;
    private EventDao eventDao;

    @Override
    public void create(EventStat target) {
        jdbcTemplate.update("INSERT INTO event_stat(event_id, count_by_event_name, count_by_event_price, count_by_ticket_booked ) VALUES(?,?,?,?)",
                target.getEvent().getId(),
                target.getCountByEventName(),
                target.getCountByEventPrice(),
                target.getCountByTicketBooked());
    }

    @Override
    public void update(EventStat target) {
        jdbcTemplate.update("UPDATE event_stat SET event_id=?, count_by_event_name=?, count_by_event_price=?, count_by_ticket_booked=? WHERE id=?",
                target.getEvent().getId(),
                target.getCountByEventName(),
                target.getCountByEventPrice(),
                target.getCountByTicketBooked(),
                target.getId());
    }

    @Override
    public void delete(EventStat target) {
        jdbcTemplate.update("DELETE FROM event_stat WHERE id=?", target.getId());
    }

    @Override
    public EventStat getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM event_stat WHERE id=?", new Object[]{id},
                getEventStatRowMapper());
    }

    @Override
    public List<EventStat> getAllEventStats() {
        return jdbcTemplate.query("SELECT * FROM event_stat", getEventStatRowMapper());
    }

    private RowMapper<EventStat> getEventStatRowMapper() {
        return (rs, i) -> {
            EventStat eventStat = new EventStat();
            eventStat.setId(rs.getInt("id"));
            eventStat.setEvent(eventDao.getById(rs.getInt("event_id")));
            eventStat.setCountByEventName(rs.getInt("count_by_event_name"));
            eventStat.setCountByEventPrice(rs.getInt("count_by_event_price"));
            eventStat.setCountByTicketBooked(rs.getInt("count_by_ticket_booked"));
            return eventStat;
        };
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public EventDao getEventDao() {
        return eventDao;
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }
}
