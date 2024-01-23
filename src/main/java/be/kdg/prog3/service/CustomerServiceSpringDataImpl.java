package be.kdg.prog3.service;

import be.kdg.prog3.domain.Customer;
import be.kdg.prog3.domain.Gender;
import be.kdg.prog3.domain.Purchase;
import be.kdg.prog3.repository.Customers.SpringDataJpaCustomerRepository;
import be.kdg.prog3.repository.WriteToJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("spring_data")
public class CustomerServiceSpringDataImpl implements CustomerService {
    private final SpringDataJpaCustomerRepository springDataJpaCustomerRepository;
    private final WriteToJson writeToJson;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CustomerServiceSpringDataImpl(SpringDataJpaCustomerRepository springDataJpaCustomerRepository, WriteToJson writeToJson) {
        this.springDataJpaCustomerRepository = springDataJpaCustomerRepository;
        this.writeToJson = writeToJson;
    }

    @Override
    public Customer add(Customer customer) {
        logger.info("Adding customer named {} ", customer.getFirstName());
        return springDataJpaCustomerRepository.save(customer);
    }

    //TODO check if this is correct
    @Override
    public Customer deleteCustomer(int customerId) {
        logger.info("Deleting customer with id {} ", customerId);
        springDataJpaCustomerRepository.deleteById(customerId);
        return springDataJpaCustomerRepository.findAllById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        logger.info("finding all customers...");
        return springDataJpaCustomerRepository.findAll();
    }

    @Override
    public List<Customer> getAllCustomersByGender(Gender gender) {
        logger.info("finding all customers with the gender {}...", gender.toString());
        return springDataJpaCustomerRepository.findAllByGender(gender);
    }

    @Override
    public Customer customerByName(String firstName) {
        logger.info("Getting customer by name...");
        return springDataJpaCustomerRepository.findAllByFirstName(firstName).get(0);
    }

    //TODO: check if this is correct
    @Override
    public Customer customerById(int customerId) {
        logger.info("Getting customer by id...");
        return springDataJpaCustomerRepository.findAllById(customerId);
    }

    @Override
    public List<Purchase> getPurchaseByCustomer(int customerId) {
        logger.info("Getting purchases by customer...");
        return springDataJpaCustomerRepository.findAllById(customerId).getPurchases();
    }

    @Override
    public void writeCustomerToJson(List<Customer> customers) {
        logger.info("Writing customers to JSON...");
        writeToJson.writeCustomers(customers);
    }


    @Override
    public List<Customer> findAllByEmailIsNotContaining(String email) {
        logger.info("Finding customers by email...");
        return springDataJpaCustomerRepository.findAllByEmailIsNotContaining(email);
    }

    @Override
    public List<Customer> findAllByLastNameStartingWith(String prefix) {
        logger.info("Finding customers by last name starting with {}...", prefix);
        return springDataJpaCustomerRepository.findAllByLastNameStartingWith(prefix);
    }

}
