package be.kdg.prog3.repository.Customers;

import be.kdg.prog3.domain.Customer;
import be.kdg.prog3.domain.Gender;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

@Repository
@Profile("spring_data")
public interface SpringDataJpaCustomerRepository extends JpaRepository<Customer, Integer> {

    //method queries
    List<Customer> findAllByEmailIsNotContaining(String email);
    List<Customer> findAllByLastNameStartingWith(String prefix);


    //custom query methods
    @Query("SELECT c FROM Customer c WHERE c.gender = 'M'")
    List<Customer> findAllByGender(Gender gender);

    @Query("SELECT c FROM Customer c WHERE c.firstName = ?1")
    List<Customer> findAllByFirstName(String firstName);

    @Query("SELECT c FROM Customer c WHERE c.customerId = ?1")
    Customer findAllById(int customerId);
}
