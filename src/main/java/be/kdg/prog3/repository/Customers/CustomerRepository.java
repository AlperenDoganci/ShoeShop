package be.kdg.prog3.repository.Customers;

import be.kdg.prog3.domain.Customer;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CustomerRepository {

    Customer createCustomer(Customer customer);

    @Transactional
    void updateCustomer(Customer customer);

    void deleteCustomer(int customerId);

    List<Customer> findAllCustomers();
    Customer findCustomerByName(String firstName);

    Customer findCustomerById(int customerId);
}
