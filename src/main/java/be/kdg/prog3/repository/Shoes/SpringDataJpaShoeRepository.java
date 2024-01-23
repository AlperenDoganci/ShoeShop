package be.kdg.prog3.repository.Shoes;

import be.kdg.prog3.domain.Shoe;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Profile("spring_data")
public interface SpringDataJpaShoeRepository extends JpaRepository<Shoe, Integer> {

    //method queries
    List<Shoe> findAllByBrand(String brand);

    List<Shoe> findAllByPriceGreaterThan(double price);


    //custom query methods
    @Query("SELECT s FROM Shoe s WHERE s.shoeId = ?1")
    Shoe findAllByShoeId(int shoeId);

    @Query("SELECT s FROM Shoe s WHERE s.price < ?1")
    List<Shoe> findAllByPriceLessThan(double price);

}
