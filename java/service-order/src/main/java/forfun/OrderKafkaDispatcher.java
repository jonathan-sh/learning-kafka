package forfun;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class OrderKafkaDispatcher
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
       try(var dispatcherOrder = new KafkaDispatcher<Order>())
       {
           var key = UUID.randomUUID().toString();
           var id = UUID.randomUUID().toString();
           var amount = BigDecimal.valueOf(Math.random() * 5000 + 1);
           var order = new Order(id, amount);
           System.out.println("order dispatcher produced a new order");
           dispatcherOrder.send("new_order", key, order);
       }
    }
}
