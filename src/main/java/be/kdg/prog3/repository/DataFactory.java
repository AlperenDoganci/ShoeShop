package be.kdg.prog3.repository;

import be.kdg.prog3.domain.*;
import be.kdg.prog3.repository.Customers.CustomerRepositoryImpl;
import be.kdg.prog3.repository.Shoes.ShoeRepositoryImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("list_template")
public class DataFactory implements CommandLineRunner {

    private CustomerRepositoryImpl customerRepository;
    private ShoeRepositoryImpl shoeRepository;

    public DataFactory(CustomerRepositoryImpl customerRepository, ShoeRepositoryImpl shoeRepository) {
        this.customerRepository = customerRepository;
        this.shoeRepository = shoeRepository;
    }



    public void seed() {
        List<Customer> customers = new ArrayList<>();
        List<Shoe> shoes = new ArrayList<>();

        Customer customer1 = new Customer( "Michael", "Scott", "michaelscott@gmail.com", "123456789", LocalDate.of(1972, 10, 1), Gender.M);
        customers.add(customer1);
        Customer customer2 = new Customer("Jim", "Halpert", "jimhalpert@gmail.com", "545632189", LocalDate.of(1982, 9, 2), Gender.M);
        customers.add(customer2);
        Customer customer3 = new Customer("Dwight", "Baker", "dwightbaker@gmail.com", "456789123", LocalDate.of(1976, 12, 24), Gender.M);
        customers.add(customer3);
        Customer customer4 = new Customer("Angela", "Johnson", "angelajohnson@gmail.com", "756984156", LocalDate.of(1974, 8, 6), Gender.F);
        customers.add(customer4);
        Customer customer5 = new Customer("Pam", "Beesly","pambeesly@outlook.com","756985445", LocalDate.of(1985, 6, 18), Gender.F);
        customers.add(customer5);

        Shoe shoe1 = new Shoe("nike", 41, "red", 90);
        shoes.add(shoe1);
        Shoe shoe2 = new Shoe("puma", 43, "white", 120);
        shoes.add(shoe2);
        Shoe shoe3 = new Shoe("adidas", 42, "black", 190);
        shoes.add(shoe3);
        Shoe shoe4 = new Shoe("nike", 37, "orange", 100);
        shoes.add(shoe4);
        Shoe shoe5 = new Shoe("cat", 41, "yellow", 200);
        shoes.add(shoe5);


        customer1.addShoe(shoe1);
        customer1.addShoe(shoe2);

        customer2.addShoe(shoe2);
        customer2.addShoe(shoe3);
        customer2.addShoe(shoe4);

        customer3.addShoe(shoe3);
        customer3.addShoe(shoe1);

        customer4.addShoe(shoe2);
        customer4.addShoe(shoe3);

        customer5.addShoe(shoe5);

        Purchase purchase1 = new Purchase(1, LocalDate.of(2023, 10, 3), 100, PaymentMethod.CREDIT_CARD);
        Purchase purchase2 = new Purchase(2, LocalDate.of(2023, 10, 4), 210, PaymentMethod.PAYPAL);
        Purchase purchase3 = new Purchase(3, LocalDate.of(2023, 10, 5), 165, PaymentMethod.CASH);
        Purchase purchase4 = new Purchase(4, LocalDate.of(2023, 10, 6), 65, PaymentMethod.CASH);
        Purchase purchase5 = new Purchase(5, LocalDate.of(2023, 10, 7), 120, PaymentMethod.CREDIT_CARD);

        shoe1.addPurchase(purchase1);
        purchase1.addShoe(shoe1);

        shoe2.addPurchase(purchase1);
        purchase1.addShoe(shoe2);

        shoe2.addPurchase(purchase2);
        purchase2.addShoe(shoe2);

        shoe3.addPurchase(purchase2);
        purchase2.addShoe(shoe3);
        shoe3.addPurchase(purchase3);
        purchase3.addShoe(shoe3);

        shoe4.addPurchase(purchase4);
        purchase4.addShoe(shoe4);
        shoe4.addPurchase(purchase2);
        purchase2.addShoe(shoe4);

        shoe5.addPurchase(purchase5);
        purchase5.addShoe(shoe5);

        customers.forEach(customerRepository::createCustomer);
        shoes.forEach(shoeRepository::createShoe);

    }
    @Override
    public void run(String... args) throws Exception {
        seed();
    }

}
