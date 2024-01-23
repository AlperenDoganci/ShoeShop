package be.kdg.prog3.repository.purchases;

import be.kdg.prog3.domain.Purchase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("jpa")
@Repository
public class JPAPurchaseRepositoryImpl implements PurchaseRepository {

    @PersistenceContext
    private EntityManager em;
    private final Logger logger = LoggerFactory.getLogger(JPAPurchaseRepositoryImpl.class);
    @Override
    @Transactional
    public Purchase createPurchase(Purchase purchase) {
        logger.info("Creating purchase {}", purchase);
        em.persist(purchase);
        return purchase;
    }

    @Override
    @Transactional
    public void updatePurchase(Purchase purchase) {
        logger.info("Updating purchase {}", purchase);
        em.merge(purchase);
    }
    @Override
    @Transactional
    public List<Purchase> findAllPurchases() {
        logger.info("Getting purchases...");
        List<Purchase> purchases = em.createQuery("SELECT p FROM Purchase p").getResultList();
        return purchases;
    }

    @Override
    @Transactional
    public Purchase findPurchaseById(int purchaseId) {
        logger.info("Purchase by id: " + purchaseId);
        Purchase purchase = em.find(Purchase.class, purchaseId);
        return purchase;
    }

    @Override
    @Transactional
    public List<Purchase> getPurchasesForCustomer(int customerId) {
        logger.info("Getting purchases for customer with id: " + customerId);
        return em.createQuery("SELECT p FROM Purchase p WHERE p.customer.customerId = :customerId", Purchase.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Override
    @Transactional
    public List<Purchase> getPurchasesForShoe(int shoeId) {
        logger.info("Getting purchases for shoe with id: " + shoeId);
        return em.createQuery("SELECT p FROM Purchase p JOIN p.shoeList s WHERE s.shoeId = :shoeId", Purchase.class)
                .setParameter("shoeId", shoeId)
                .getResultList();
    }
}
