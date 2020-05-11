package forfun;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class GsonDeserializer<T> implements Deserializer<T>
{
    public static final String TYPE_CONFIG = "";
    private final Gson gson = new GsonBuilder().create();
    private Class<T> type;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey)
    {
        try
        {
            var typeName = String.valueOf(configs.get(TYPE_CONFIG));
            this.type = (Class<T>) Class.forName(typeName);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public T deserialize(String s, byte[] bytes)
    {
        return gson.fromJson(new String(bytes), type);
    }
}
