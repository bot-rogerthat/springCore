package spring.core.dao;

import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.DiscountStat;

import java.util.List;

public interface DiscountStatDao {
    void create(DiscountStat target) throws DaoException;

    void update(DiscountStat target) throws DaoException;

    void delete(int id) throws DaoException;

    DiscountStat getById(int id) throws DaoException;

    List<DiscountStat> getAllDiscountStats() throws DaoException;
}
