package spring.core.dao;

import spring.core.entity.DiscountStat;

import java.util.List;

public interface DiscountStatDao {
    void create(DiscountStat target);

    void update(DiscountStat target);

    void delete(DiscountStat target);

    DiscountStat getById(int id);

    List<DiscountStat> getAllDiscountStats();
}
