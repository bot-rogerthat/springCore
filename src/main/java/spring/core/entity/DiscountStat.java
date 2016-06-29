package spring.core.entity;

public class DiscountStat {
    private int id;
    private int userId;
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
