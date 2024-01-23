package be.kdg.prog3.service;

import be.kdg.prog3.domain.Customer;
import be.kdg.prog3.domain.Gender;
import be.kdg.prog3.domain.PaymentMethod;
import be.kdg.prog3.domain.Purchase;
import be.kdg.prog3.repository.Customers.CustomerRepository;
import be.kdg.prog3.repository.WriteToJson;
import be.kdg.prog3.repository.purchases.PurchaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Profile({"jdbc_template","jpa", "list_template"})
public class CustomerServiceImpl implements CustomerService {

    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final PurchaseRepository purchaseRepository;
    private final WriteToJson writeToJson;

    public CustomerServiceImpl(CustomerRepository customerRepository,PurchaseRepository purchaseRepository, WriteToJson writeToJson) {
        this.customerRepository = customerRepository;
        this.purchaseRepository = purchaseRepository;
        this.writeToJson = writeToJson;
    }

    @Override
    public Customer add(Customer customer) {
        logger.info("Adding customer named {} ", customer.getFirstName());;
        return customerRepository.createCustomer(customer);
    }
    @Override
    public Customer deleteCustomer(int customerId) {
        logger.info("Deleting customer with id {} ", customerId);
        Customer deletedCustomer = customerRepository.findCustomerById(customerId);
        customerRepository.deleteCustomer(customerId);
        return deletedCustomer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAllCustomers();
    }

    @Override
    public List<Customer> getAllCustomersByGender(Gender gender) {
        return customerRepository.findAllCustomers().stream().
                filter(customer -> customer.getGender().equals(gender)).toList();
    }

    public Customer customerByName(String firstName) {
        return customerRepository.findCustomerByName(firstName);
    }

    @Override
    public Customer customerById(int customerId) {
        return customerRepository.findCustomerById(customerId);
    }

    @Override
    public List<Purchase> getPurchaseByCustomer(int customerId) {
        return purchaseRepository.getPurchasesForCustomer(customerId);
    }

    @Override
    public void writeCustomerToJson(List<Customer> customers) {
        writeToJson.writeCustomers(customers);
    }

    @Override
    public List<Customer> findAllByEmailIsNotContaining(String email) {
        return customerRepository.findAllCustomers().stream().
                filter(customer -> !customer.getEmail().contains(email)).toList();
    }

    @Override
    public List<Customer> findAllByLastNameStartingWith(String prefix) {
        return customerRepository.findAllCustomers().stream().
                filter(customer -> customer.getLastName().startsWith(prefix)).toList();
    }
}
