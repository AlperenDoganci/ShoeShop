package be.kdg.prog3.service;

import be.kdg.prog3.domain.Customer;
import be.kdg.prog3.domain.Gender;
import be.kdg.prog3.domain.Purchase;

import java.util.List;

public interface CustomerService {
    Customer add(Customer customer);

    Customer deleteCustomer(int customerId);

    List<Customer> getAllCustomers();

    List<Customer> getAllCustomersByGender(Gender gender);

    Customer customerByName(String firstName);

    Customer customerById(int customerId);

    List<Purchase> getPurchaseByCustomer(int customerId);

    void writeCustomerToJson(List<Customer> customers);

    List<Customer> findAllByEmailIsNotContaining(String email);

    List<Customer> findAllByLastNameStartingWith(String prefix);
}