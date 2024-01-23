package be.kdg.prog3.repository.purchases;

import be.kdg.prog3.domain.Purchase;
import jakarta.transaction.Transactional;

import java.util.List;

public interface PurchaseRepository {
    Purchase createPurchase(Purchase purchase);

    @Transactional
    void updatePurchase(Purchase purchase);

    List<Purchase> findAllPurchases();

    Purchase findPurchaseById(int purchaseId);

    List<Purchase> getPurchasesForCustomer(int customerId);

    List<Purchase> getPurchasesForShoe(int shoeId);
}
