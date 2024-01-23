package be.kdg.prog3.repository.Customers;

import be.kdg.prog3.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("list_template")
public class CustomerRepositoryImpl implements CustomerRepository {
    private static final List<Customer> customers = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepositoryImpl.class);
    @Override
    public List<Customer> findAllCustomers() {
        logger.info("Getting customers...");
        return customers;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setCustomerId(customers.size());
        logger.info("Creating customer {}", customer);
        customers.add(customer);
        return customer;
    }

    @Override
    public void updateCustomer(Customer updatedCustomer) {
        logger.info("Updating customer with id: {}", updatedCustomer.getCustomerId());

        for (Customer existingCustomer : customers) {
            if (existingCustomer.getCustomerId() == updatedCustomer.getCustomerId()) {
                existingCustomer.setFirstName(updatedCustomer.getFirstName());
                existingCustomer.setLastName(updatedCustomer.getLastName());
                existingCustomer.setEmail(updatedCustomer.getEmail());
                existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
                existingCustomer.setDateOfBirth(updatedCustomer.getDateOfBirth());
                existingCustomer.setGender(updatedCustomer.getGender());

                logger.info("Customer updated: {}", existingCustomer);
                return;
            }
        }
    }

    @Override
    public Customer findCustomerByName(String firstName) {
        logger.debug("Customer by name: " + firstName);
        for (Customer customer : customers) {
            if (customer.getFirstName().equals(firstName)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public Customer findCustomerById(int customerId) {
        logger.debug("Customer by id: " + customerId);
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public void deleteCustomer(int customerId) {
        logger.info("Deleting customer with id: " + customerId);
        customers.removeIf(customer -> customer.getCustomerId() == customerId);
    }

}
