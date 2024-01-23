package be.kdg.prog3.service;

import be.kdg.prog3.domain.Purchase;
import be.kdg.prog3.domain.Shoe;
import be.kdg.prog3.exceptions.DatabaseException;
import be.kdg.prog3.exceptions.ShoeException;
import be.kdg.prog3.repository.Shoes.ShoeRepository;
import be.kdg.prog3.repository.WriteToJson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile({"jdbc_template","jpa", "list_template"})
public class ShoeServiceImpl implements ShoeService {
    private final Logger logger = LoggerFactory.getLogger(ShoeServiceImpl.class);
    private ShoeRepository shoeRepository;
    private WriteToJson writeToJson;

    public ShoeServiceImpl(ShoeRepository repository, WriteToJson writeToJson) {
        this.shoeRepository = repository;
        this.writeToJson = writeToJson;
    }

    @Override
    public Shoe addShoe(Shoe shoe) {
        logger.info("Adding shoe with the brand {} ", shoe.getBrand());

        try {
            if (shoeRepository.findShoeById(shoe.getShoeId()) != null) {
                throw new ShoeException("Shoe already exists!");
            }
            return shoeRepository.createShoe(shoe);
        } catch (Exception e) {
            logger.error("Error occurred while adding a shoe:", e);
            throw new DatabaseException("Error adding a shoe: " + e.getMessage());
        }
    }

    @Override
    public Shoe deleteShoe(int shoeId) {
        logger.info("Deleting shoe with id {} ", shoeId);
        Shoe deletedShoe = shoeRepository.findShoeById(shoeId);
        if (deletedShoe == null) {
            throw new ShoeException("Shoe not found!");
        }
        shoeRepository.deleteShoe(shoeId);
        return deletedShoe;
    }

    @Override
    public List<Shoe> getAll() {
        return shoeRepository.findAllShoes();
    }

    @Override
    public List<String> getAllBrands() {
        return shoeRepository.findAllShoes().stream().map(Shoe::getBrand).collect(Collectors.toList());
    }

    @Override
    public Shoe shoeById(int shoeId) {
        return shoeRepository.findShoeById(shoeId);
    }

    @Override
    public void writeShoesToJson(Collection<Shoe> shoes) {
        writeToJson.writeShoes(shoes);
    }

    @Override
    public List<Shoe> getAllByBrand(String brand) {
        String lowerCaseBrand = brand.toLowerCase();
        logger.info("Searching for shoes with brand {} ", lowerCaseBrand);
        List<Shoe> shoes = shoeRepository.findAllShoes().stream()
                .filter(shoe -> shoe.getBrand().toLowerCase().contains(lowerCaseBrand))
                .collect(Collectors.toList());

        if (shoes.isEmpty()) {
            logger.info("Brand not found !");
            throw new ShoeException("Brand not found!");
        }

        return shoes;
    }
    @Override
    public List<Shoe> findAllShoesByPriceGreaterThan(double price) {
        return shoeRepository.findAllShoes().stream().filter(shoe -> shoe.getPrice() > price).collect(Collectors.toList());
    }
    @Override
    public List<Shoe> findAllShoesByPriceLessThan(double price) {
        return shoeRepository.findAllShoes().stream().filter(shoe -> shoe.getPrice() < price).collect(Collectors.toList());
    }

    @Override
    public List<Purchase> getPurchaseByShoe(int shoeId) {
        return shoeRepository.findShoeById(shoeId).getPurchases();
    }
}
