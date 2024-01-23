package be.kdg.prog3.repository.Customers;

import be.kdg.prog3.domain.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("jpa")
@Repository
public class JPACustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;
    private Logger logger = LoggerFactory.getLogger(JPACustomerRepositoryImpl.class);
    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        logger.info("Creating customer {}", customer);
        em.persist(customer);
        return customer;
    }
    @Override
    @Transactional
    public void updateCustomer(Customer customer) {
        logger.info("Updating customer {}", customer);
        em.merge(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(int customerId) {
        logger.info("Deleting customer with id: " + customerId);
        Customer customer = em.find(Customer.class, customerId);
        em.remove(customer);
    }

    @Override
    @Transactional
    public List<Customer> findAllCustomers() {
        logger.info("Getting customers...");
        List<Customer> customers =em.createQuery("SELECT c FROM Customer c").getResultList();
        return customers;
    }

    @Override
    @Transactional
    public Customer findCustomerByName(String firstName) {
        logger.info("Customer by name: " + firstName);
        try {
            String query = "SELECT c FROM Customer c WHERE c.firstName = :firstName";
            TypedQuery<Customer> typedQuery = em.createQuery(query, Customer.class);
            typedQuery.setParameter("firstName", firstName);
            return typedQuery.getSingleResult();
        } catch (NoResultException ex) {
            // No customer found with the given firstname
            return null;
        }
    }

    @Override
    @Transactional
    public Customer findCustomerById(int customerId) {
        logger.info("Customer by id: " + customerId);
        Customer customer = em.find(Customer.class, customerId);
        return customer;
    }
}
