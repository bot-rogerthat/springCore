package spring.core.service;

import spring.core.dao.DiscountStatDao;
import spring.core.entity.DiscountStat;
import spring.core.entity.User;

import java.util.List;

public class DiscountStatService {
    private DiscountStatDao discountStatDao;

    public DiscountStat getDiscountStat(User user, String discountName) {
        DiscountStat discountStat = getDiscountStatByUserAndDiscountName(user, discountName);
        if (discountStat == null) {
            discountStat = new DiscountStat();
            discountStat.setUser(user);
            discountStat.setDiscountName(discountName);
            discountStat.setCount(0);
            discountStatDao.create(discountStat);
        }
        return getDiscountStatByUserAndDiscountName(user, discountName);
    }

    public void updateStat(DiscountStat target) {
        discountStatDao.update(target);
    }


    private DiscountStat getDiscountStatByUserAndDiscountName(User target, String discountName) {
        return discountStatDao.getAllDiscountStats().stream()
                .filter(ds -> ds.getUser().getId() == target.getId())
                .filter(ds -> ds.getDiscountName().equals(discountName))
                .findFirst()
                .orElse(null);
    }

    public List<DiscountStat> getAllEventStats() {
        return discountStatDao.getAllDiscountStats();
    }


    public void setDiscountStatDao(DiscountStatDao discountStatDao) {
        this.discountStatDao = discountStatDao;
    }
}
