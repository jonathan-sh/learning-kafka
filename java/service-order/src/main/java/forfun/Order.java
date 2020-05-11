package forfun;

import java.math.BigDecimal;

public class Order
{
    String id;
    BigDecimal amount;

    public Order(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                '}';
    }
}