package be.kdg.prog3.service;

import be.kdg.prog3.domain.Purchase;
import be.kdg.prog3.domain.Shoe;
import be.kdg.prog3.exceptions.ShoeException;
import be.kdg.prog3.repository.Shoes.SpringDataJpaShoeRepository;
import be.kdg.prog3.repository.WriteToJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("spring_data")
public class ShoeServiceSpringDataImpl implements ShoeService {
    private final SpringDataJpaShoeRepository springDataJpaShoeRepository;
    private final WriteToJson writeToJson;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ShoeServiceSpringDataImpl(SpringDataJpaShoeRepository springDataJpaShoeRepository, WriteToJson writeToJson) {
        this.springDataJpaShoeRepository = springDataJpaShoeRepository;
        this.writeToJson = writeToJson;
    }
    @Override
    public Shoe addShoe(Shoe shoe) {
        logger.info("Adding shoe {} ", shoe);
        return springDataJpaShoeRepository.save(shoe);
    }

    @Override
    public Shoe deleteShoe(int shoeId) {
        logger.info("Deleting shoe with id {} ", shoeId);
        springDataJpaShoeRepository.deleteById(shoeId);
        return springDataJpaShoeRepository.findAllByShoeId(shoeId);
    }

    @Override
    public List<Shoe> getAll() {
        logger.info("Getting all shoes...");
        return springDataJpaShoeRepository.findAll();
    }

    @Override
    public List<String> getAllBrands() {
        logger.info("Getting all brands...");
        return springDataJpaShoeRepository.findAll().stream().map(Shoe::getBrand).distinct().toList();
    }

    @Override
    public Shoe shoeById(int shoeId) {
        logger.info("Getting shoe by id...");
        return springDataJpaShoeRepository.findAllByShoeId(shoeId);
    }

    @Override
    public void writeShoesToJson(Collection<Shoe> shoes) {
        writeToJson.writeShoes(shoes);
    }


    //Usage of ShoeException
    @Override
    public List<Shoe> getAllByBrand(String brand) {
        String lowerCaseBrand = brand.toLowerCase();

        List<Shoe> shoes = springDataJpaShoeRepository.findAllByBrand(brand);

        shoes = shoes.stream()
                .filter(shoe -> shoe.getBrand().toLowerCase().contains(lowerCaseBrand))
                .collect(Collectors.toList());

        if (shoes.isEmpty()) {
            throw new ShoeException("Brand not found!");
        }

        return shoes;
    }

    @Override
    public List<Shoe> findAllShoesByPriceGreaterThan(double price) {
        logger.info("Getting all shoes by price greater than {}...", price);
        return springDataJpaShoeRepository.findAllByPriceGreaterThan(price);
    }

    @Override
    public List<Shoe> findAllShoesByPriceLessThan(double price) {
        logger.info("Getting all shoes by price lower than {}...", price);
        return springDataJpaShoeRepository.findAllByPriceLessThan(price);
    }

    @Override
    public List<Purchase> getPurchaseByShoe(int shoeId) {
        return springDataJpaShoeRepository.findAllByShoeId(shoeId).getPurchases();
    }
}
