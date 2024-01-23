package be.kdg.prog3.repository.Shoes;

import be.kdg.prog3.domain.Shoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("list_template")
public class ShoeRepositoryImpl implements ShoeRepository {
    private static List<Shoe> shoes = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(ShoeRepositoryImpl.class);

    @Override
    public List<Shoe> findAllShoes() {
        logger.info("Getting shoes...");
        return shoes;
    }

    @Override
    public Shoe createShoe(Shoe shoe) {
        shoe.setShoeId(shoes.size());
        logger.info("Creating shoe {}", shoe);
        shoes.add(shoe);
        return shoe;
    }

    @Override
    public void updateShoe(Shoe updatedShoe) {
        logger.info("Updating shoe with id: {}", updatedShoe.getShoeId());

        for (Shoe existingShoe : shoes) {
            if (existingShoe.getShoeId() == updatedShoe.getShoeId()) {
                existingShoe.setBrand(updatedShoe.getBrand());
                existingShoe.setSize(updatedShoe.getSize());
                existingShoe.setColor(updatedShoe.getColor());
                existingShoe.setPrice(updatedShoe.getPrice());
                existingShoe.setCustomers(updatedShoe.getCustomers());

                logger.info("Shoe updated: {}", existingShoe);
                return;
            }
        }
    }

    @Override
    public List<String> getAllBrands() {
        List<String> brandList = shoes.stream()
                .map(Shoe::getBrand)
                .collect(Collectors.toList());
        return brandList;
    }

    @Override
    public Shoe findShoeById(int shoeId) {
        logger.debug("Shoe by id: " + shoeId);
        for (Shoe shoe : shoes) {
            if (shoe.getShoeId() == shoeId) {
                return shoe;
            }
        }
        return null;
    }

    @Override
    public void deleteShoe(int shoeId) {
        logger.info("Deleting shoe with id: " + shoeId);
        shoes.removeIf(shoe -> shoe.getShoeId() == shoeId);
    }
}
