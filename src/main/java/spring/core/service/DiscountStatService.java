package spring.core.service;

import spring.core.dao.DiscountStatDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.DiscountStat;
import spring.core.entity.User;

import java.util.List;

public class DiscountStatService {
    private DiscountStatDao discountStatDao;

    public DiscountStat getDiscountStat(User user, String discountName) throws DaoException {
        DiscountStat discountStat = getDiscountStatByUserAndDiscountName(user, discountName);
        if (discountStat == null) {
            discountStat = new DiscountStat();
            discountStat.setUserId(user.getId());
            discountStat.setDiscountName(discountName);
            discountStat.setCount(0);
            discountStatDao.create(discountStat);
        }
        return getDiscountStatByUserAndDiscountName(user, discountName);
    }

    public void updateStat(DiscountStat target) throws DaoException {
        discountStatDao.update(target);
    }


    private DiscountStat getDiscountStatByUserAndDiscountName(User target, String discountName) throws DaoException {
        return discountStatDao.getAllDiscountStats().stream()
                .filter(ds -> ds.getUserId() == target.getId())
                .filter(ds -> ds.getDiscountName().equals(discountName))
                .findFirst()
                .orElse(null);
    }

    public List<DiscountStat> getAllEventStats() throws DaoException {
        return discountStatDao.getAllDiscountStats();
    }


    public void setDiscountStatDao(DiscountStatDao discountStatDao) {
        this.discountStatDao = discountStatDao;
    }
}
