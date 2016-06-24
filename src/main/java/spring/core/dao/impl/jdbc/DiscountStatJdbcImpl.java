package spring.core.dao.impl.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import spring.core.dao.DiscountStatDao;
import spring.core.dao.UserDao;
import spring.core.entity.DiscountStat;

import java.util.List;

public class DiscountStatJdbcImpl implements DiscountStatDao {
    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    @Override
    public void create(DiscountStat target) {
        jdbcTemplate.update("INSERT INTO discount_stat(user_id, discount_name, count) VALUES(?,?,?)",
                target.getUser().getId(),
                target.getDiscountName(),
                target.getCount());
    }

    @Override
    public void update(DiscountStat target) {
        jdbcTemplate.update("UPDATE discount_stat SET user_id=?, discount_name=?, count=? WHERE id=?",
                target.getUser().getId(),
                target.getDiscountName(),
                target.getCount(),
                target.getId());
    }

    @Override
    public void delete(DiscountStat target) {
        jdbcTemplate.update("DELETE FROM discount_stat WHERE id=?", target.getId());
    }

    @Override
    public DiscountStat getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM discount_stat WHERE id=?", new Object[]{id},
                getDiscountStatRowMapper());
    }


    @Override
    public List<DiscountStat> getAllDiscountStats() {
        return jdbcTemplate.query("SELECT * FROM discount_stat", getDiscountStatRowMapper());
    }

    private RowMapper<DiscountStat> getDiscountStatRowMapper() {
        return (rs, i) -> {
            DiscountStat discountStat = new DiscountStat();
            discountStat.setId(rs.getInt("id"));
            discountStat.setUser(userDao.getById(rs.getInt("user_id")));
            discountStat.setDiscountName(rs.getString("discount_name"));
            discountStat.setCount(rs.getInt("count"));
            return discountStat;
        };
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