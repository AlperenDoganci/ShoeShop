package be.kdg.prog3.repository;

import be.kdg.prog3.domain.Customer;
import be.kdg.prog3.domain.Shoe;
import be.kdg.prog3.util.LocalDateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class WriteToJson {
    private static final String customerJson = "customer.json";
    private static final String shoeJson = "shoe.json";

    private final Logger logger = LoggerFactory.getLogger(WriteToJson.class);


    private Gson gson;
    public WriteToJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(new TypeToken<LocalDate>(){}.getType(), new LocalDateSerializer());
        gson = gsonBuilder.create();
    }

    public void writeCustomers(Collection<Customer> customers) {
        logger.info("Writing customers to Json {}", customers);
        String json = gson.toJson(customers);
        try (FileWriter writer = new FileWriter(customerJson)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Saving customers to Json failed");
        }
    }

    public void writeShoes(Collection<Shoe> shoes) {
        logger.info("Writing shoes to Json {}", shoes);
        String json = gson.toJson(shoes);
        try (FileWriter writer = new FileWriter(shoeJson)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Saving shoes to json failed");
        }
    }
}
