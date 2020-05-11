package forfun;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class OrderKafkaService
{

    public static void main(String[] args)
    {
        try(var service = new KafkaService("new_order",
                OrderKafkaService.class,
                new OrderKafkaService()::parse,
                Order.class,
                Map.of()))
        {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Order> record)
    {
        try
        {
            System.out.println("order service consumes a new order...");
            System.out.println("offset: " + record.offset());
            System.out.println("key: " + record.key());
            System.out.println("value: " + record.value().toString());
            Thread.sleep(20);
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }
}
