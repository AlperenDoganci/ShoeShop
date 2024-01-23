package be.kdg.prog3.repository.purchases;

import be.kdg.prog3.domain.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("list_template")
public class PurchaseRepositoryImpl implements PurchaseRepository{

    private static final List<Purchase> purchases = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(PurchaseRepositoryImpl.class);
    @Override
    public Purchase createPurchase(Purchase purchase) {
        purchase.setPurchaseId(purchases.size());
        logger.info("Creating purchase {}", purchase);
        purchases.add(purchase);
        return purchase;
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        logger.info("Updating purchase with id {}...", purchase.getPurchaseId());
        for (Purchase existingPurchase : purchases) {
            if (existingPurchase.getPurchaseId() == purchase.getPurchaseId()) {
                existingPurchase.setPurchaseDate(purchase.getPurchaseDate());
                existingPurchase.setTotalPrice(purchase.getTotalPrice());
                existingPurchase.setPaymentMethod(purchase.getPaymentMethod());
                existingPurchase.setCustomer(purchase.getCustomer());

                logger.info("Purchase updated: {}", existingPurchase);
                return;
            }
        }
    }

    @Override
    public List<Purchase> findAllPurchases() {
        logger.info("Getting purchases...");
        return purchases;
    }

    @Override
    public Purchase findPurchaseById(int purchaseId) {
        logger.info("Getting purchase by id: {}", purchaseId);
        for (Purchase purchase : purchases) {
            if (purchase.getPurchaseId() == purchaseId) {
                return purchase;
            }
        }
        return null;
    }

    @Override
    public List<Purchase> getPurchasesForCustomer(int customerId) {
        return purchases.stream()
                .filter(purchase -> purchase.getCustomer_id() == customerId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Purchase> getPurchasesForShoe(int shoeId) {
        return null;
    }
}
