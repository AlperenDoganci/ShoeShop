package be.kdg.prog3.repository.Customers;

import be.kdg.prog3.domain.Customer;
import be.kdg.prog3.domain.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("jdbc_template")
public class JDBCCustomerRepositoryImpl implements CustomerRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert customerInserter;
    private final static Logger logger = LoggerFactory.getLogger(JDBCCustomerRepositoryImpl.class);

    public JDBCCustomerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        logger.info("Creating JDBCCustomerRepositoryImpl...");
        this.jdbcTemplate = jdbcTemplate;
        this.customerInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("customers")
                .usingGeneratedKeyColumns("customer_id");
    }

    public static Customer mapRow(ResultSet rs, int rowid) throws SQLException {
        logger.info("Mapping row {} to customer...", rowid);
        return new Customer(rs.getInt("customer_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getDate("date_of_birth").toLocalDate(),
                Gender.valueOf(rs.getString("gender")));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("first_name", customer.getFirstName());
        parameters.put("last_name", customer.getLastName());
        parameters.put("email", customer.getEmail());
        parameters.put("phone_number",customer.getPhoneNumber());
        parameters.put("date_of_birth", customer.getDateOfBirth());
        parameters.put("gender", customer.getGender());

        customer.setCustomerId(customerInserter.executeAndReturnKey(parameters).intValue());

        logger.info("Creating customer {}", customer);
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        logger.info("Updating customer {}", customer);
        jdbcTemplate.update("UPDATE CUSTOMERS SET first_name= ?,last_name=? ,email=? ,phone_number=? ,date_of_birth=? ,gender=? WHERE customer_id = ?",
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getDateOfBirth(),
                customer.getGender(),
                customer.getCustomerId());
    }

    @Override
    public List<Customer> findAllCustomers() {
        logger.info("Reading customers...");
        return jdbcTemplate.query(
                "SELECT * FROM customers", JDBCCustomerRepositoryImpl::mapRow);
    }

    @Override
    public Customer findCustomerByName(String firstName) {
        Customer customer = jdbcTemplate.queryForObject(
                " SELECT * FROM customers WHERE first_name = ? ",
                JDBCCustomerRepositoryImpl::mapRow, firstName);
        logger.info("Reading customer {}", customer);
        return customer;
    }

    @Override
    public Customer findCustomerById(int customerId) {
        Customer customer = jdbcTemplate.queryForObject(
                "SELECT * FROM customers WHERE customer_id = ?",
                JDBCCustomerRepositoryImpl::mapRow, customerId);
        logger.info("Reading customer {}", customer);
        return customer;
    }

    @Override
    public void deleteCustomer(int customerId) {
        logger.info("Deleting customer with id {}", customerId);
        jdbcTemplate.update("DELETE FROM customer_shoe WHERE customer_id = ?", customerId);
        jdbcTemplate.update("DELETE FROM purchase_shoe WHERE purchase_id IN (SELECT purchase_id FROM purchases WHERE customer_id = ?)", customerId);
        jdbcTemplate.update("DELETE FROM purchases WHERE customer_id = ?", customerId);
        jdbcTemplate.update("DELETE FROM customers WHERE customer_id = ?", customerId);
    }

}
