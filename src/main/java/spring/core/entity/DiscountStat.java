package spring.core.entity;

public class DiscountStat {
    private int id;
    private User user;
    private String discountName;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", user=" + user +
                ", discountName='" + discountName + '\'' +
                ", count=" + count +
                '}';
    }
}
