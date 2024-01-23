package be.kdg.prog3.repository.Shoes;

import be.kdg.prog3.domain.Shoe;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ShoeRepository {
    @Transactional
    void updateShoe(Shoe shoe);

    List<Shoe> findAllShoes();

    Shoe createShoe(Shoe shoe);

    void deleteShoe(int shoeId);

    List<String> getAllBrands();

    Shoe findShoeById(int shoeId);
}
