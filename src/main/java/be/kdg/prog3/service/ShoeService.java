package be.kdg.prog3.service;

import be.kdg.prog3.domain.Purchase;
import be.kdg.prog3.domain.Shoe;

import java.util.Collection;
import java.util.List;

public interface ShoeService {

    Shoe addShoe(Shoe shoe);
    Shoe deleteShoe(int shoeId);

    List<Shoe> getAll();

    List<String> getAllBrands();

    Shoe shoeById(int shoeId);
    void writeShoesToJson(Collection<Shoe> shoes);

    List<Shoe> getAllByBrand(String brand);

    List<Shoe> findAllShoesByPriceGreaterThan(double price);

    List<Shoe> findAllShoesByPriceLessThan(double price);

    List<Purchase> getPurchaseByShoe(int shoeId);
}
