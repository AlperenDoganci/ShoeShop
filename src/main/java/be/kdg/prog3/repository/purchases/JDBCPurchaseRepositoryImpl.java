package be.kdg.prog3.repository.purchases;

import be.kdg.prog3.domain.PaymentMethod;
import be.kdg.prog3.domain.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("jdbc_template")
public class JDBCPurchaseRepositoryImpl implements PurchaseRepository{
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert purchaseInserter;
    private final static Logger logger = LoggerFactory.getLogger(JDBCPurchaseRepositoryImpl.class);

    public JDBCPurchaseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        logger.info("Creating JDBCPurchaseRepositoryImpl...");
        this.jdbcTemplate = jdbcTemplate;
        this.purchaseInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("purchases")
                .usingGeneratedKeyColumns("purchase_id");
    }

    public static Purchase mapRow(ResultSet rs, int rowid) throws SQLException {
        logger.info("Mapping row {} to purchase...", rowid);
        Purchase purchase = new Purchase();
        purchase.setPurchaseId(rs.getInt("purchase_id"));
        purchase.setPurchaseDate(rs.getDate("purchase_date").toLocalDate());
        purchase.setTotalPrice(rs.getDouble("total_price"));
        purchase.setPaymentMethod(PaymentMethod.valueOf(rs.getString("payment_method")));
        purchase.setCustomer_id(rs.getInt("customer_id"));
        return purchase;
    }


    @Override
    public Purchase createPurchase(Purchase purchase) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("purchase_date", purchase.getPurchaseDate());
        parameters.put("total_price", purchase.getTotalPrice());
        parameters.put("payment_method", purchase.getPaymentMethod());
        parameters.put("customer_id", purchase.getCustomer().getCustomerId());

        purchase.setPurchaseId(purchaseInserter.executeAndReturnKey(parameters).intValue());
        logger.info("Creating purchase {}", purchase);
        return purchase;
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        logger.info("Updating purchase with id {}...", purchase.getPurchaseId());
        jdbcTemplate.update(
                "UPDATE purchases SET purchase_date = ?, total_price = ?, payment_method = ?, customer_id = ? WHERE purchase_id = ?",
                purchase.getPurchaseDate(), purchase.getTotalPrice(), purchase.getPaymentMethod(), purchase.getCustomer().getCustomerId(), purchase.getPurchaseId());
    }

    @Override
    public List<Purchase> findAllPurchases() {
        logger.info("Reading all purchases...");
        return jdbcTemplate.query(
                "SELECT * FROM purchases", JDBCPurchaseRepositoryImpl::mapRow);
    }

    @Override
    public Purchase findPurchaseById(int purchaseId) {
        Purchase purchase = jdbcTemplate.queryForObject(
                "SELECT * FROM purchases WHERE purchase_id = ?",
                JDBCPurchaseRepositoryImpl::mapRow, purchaseId);
        logger.info("Reading purchase with id {}...", purchaseId);
        return purchase;
    }

    @Override
    public List<Purchase> getPurchasesForCustomer(int customerId) {
        logger.info("Reading purchases for customer with id {}...", customerId);
        return jdbcTemplate.query(
                "SELECT * FROM purchases WHERE customer_id = ?",
                JDBCPurchaseRepositoryImpl::mapRow, customerId);
    }

    @Override
    public List<Purchase> getPurchasesForShoe(int shoeId) {
        logger.info("Reading purchases for shoe with id {}...", shoeId);
        return jdbcTemplate.query(
                "SELECT p.* FROM purchases p " +
                        "JOIN purchase_shoe ps ON p.purchase_id = ps.purchase_id " +
                        "WHERE ps.shoe_id = ?",
                JDBCPurchaseRepositoryImpl::mapRow, shoeId);
    }
}
