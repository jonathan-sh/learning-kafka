package forfun;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.io.Closeable;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class KafkaService implements Closeable
{
    private final KafkaConsumer<String, String> consumer;
    private final ConsumerFunction parse;

    KafkaService(String topic, Class groupId, ConsumerFunction parse, Class typeConfig, Map<String, String> override)
    {
        this.consumer = new KafkaConsumer<>(properties(groupId, typeConfig, override));
        this.consumer.subscribe(Collections.singleton(topic));
        this.parse = parse;
    }

    void run()
    {
        while (true)
        {
            var records = consumer.poll(Duration.ofMillis(100));
            if(!records.isEmpty())
            {
                for (ConsumerRecord<String, String> record : records)
                {
                    parse.consume(record);
                }
            }
        }
    }

    private static Properties properties(Class groupId, Class typeConfig, Map<String, String> override)
    {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId.getName());
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
        properties.setProperty(GsonDeserializer.TYPE_CONFIG, typeConfig.getName());
        properties.putAll(override);

        return properties;
    }

    @Override
    public void close()
    {
        consumer.close();
    }
}
