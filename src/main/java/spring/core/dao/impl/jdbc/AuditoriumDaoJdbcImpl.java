package spring.core.dao.impl.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import spring.core.dao.AuditoriumDao;
import spring.core.entity.Auditorium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuditoriumDaoJdbcImpl implements AuditoriumDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Auditorium target) {
        Map<String, Object> args = new HashMap<>();
        args.put("name", target.getName());
        args.put("number_of_seats", target.getNumberOfSeats());
        SimpleJdbcInsert ins = new SimpleJdbcInsert(jdbcTemplate).withTableName("auditorium").usingGeneratedKeyColumns("id");
        int id = ins.executeAndReturnKey(args).intValue();
        target.getVips().stream().forEach(vipSeat ->
                jdbcTemplate.update("INSERT INTO vip_seat(auditorium_id, seat_number) VALUES(?,?)", id, vipSeat));
    }

    @Override
    public void update(Auditorium target) {
        jdbcTemplate.update("UPDATE auditorium SET name = ?, number_of_seats = ?  WHERE id = ?",
                target.getName(),
                target.getNumberOfSeats(),
                target.getId());
        //// TODO: 6/22/2016 vips update
    }

    @Override
    public void delete(Auditorium target) {
        jdbcTemplate.update("DELETE FROM auditorium WHERE id = ?", target.getId());
    }

    @Override
    public Auditorium getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM auditorium WHERE id = ?",
                new Object[]{id},
                getAuditoriumRowMapper());

    }

    @Override
    public List<Auditorium> getAllAuditoriums() {
        return jdbcTemplate.query("SELECT * FROM auditorium", getAuditoriumRowMapper());
    }

    private RowMapper<Auditorium> getAuditoriumRowMapper() {
        return (rs, i) -> {
            Auditorium auditorium = new Auditorium();
            auditorium.setId(rs.getInt("id"));
            auditorium.setName(rs.getString("name"));
            auditorium.setNumberOfSeats(rs.getInt("number_of_seats"));
            auditorium.setVips(getVipSeats(auditorium.getId()));
            return auditorium;
        };
    }

    private List<Integer> getVipSeats(Integer id) {
        return jdbcTemplate.query("SELECT * FROM vip_seat WHERE auditorium_id = ?", new Object[]{id},
                (rs, rowNum) -> {
                    return rs.getInt("seat_number");
                });
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}