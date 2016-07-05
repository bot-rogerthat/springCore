package spring.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "discount_stat")
public class DiscountStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "discount_name")
    private String discountName;

    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DiscountStat{" +
                "id=" + id +
                ", userId=" + userId +
                ", discountName='" + discountName + '\'' +
                ", count=" + count +
                '}';
    }
}
