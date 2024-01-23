package be.kdg.prog3.repository.Shoes;

import be.kdg.prog3.domain.Shoe;
import jakarta.persistence.EntityManager;
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
public class JPAShoeRepositoryImpl implements ShoeRepository {

    @PersistenceContext
    private EntityManager em;
    private Logger logger= LoggerFactory.getLogger(JPAShoeRepositoryImpl.class);

    public JPAShoeRepositoryImpl() {
    }
    @Override
    @Transactional
    public Shoe createShoe(Shoe shoe) {
        logger.info("Creating shoe {}", shoe);
        em.persist(shoe);
        return shoe;
    }

    @Override
    @Transactional
    public void updateShoe(Shoe shoe) {
        logger.info("Updating shoe {}", shoe);
        em.merge(shoe);
    }

    @Override
    @Transactional
    public void deleteShoe(int shoeId) {
        logger.info("Deleting shoe with id: " + shoeId);
        Shoe shoe = em.find(Shoe.class, shoeId);
        em.remove(shoe);
    }

    @Override
    @Transactional
    public List<Shoe> findAllShoes() {
        logger.info("Getting shoes...");
        List<Shoe> shoes = em.createQuery("SELECT s FROM Shoe s").getResultList();
        return shoes;
    }

    @Override
    @Transactional
    public List<String> getAllBrands() {
        String query = "SELECT s.brand FROM Shoe s WHERE s.brand = :brand";
        TypedQuery<Shoe> typedQuery = em.createQuery(query, Shoe.class);
        typedQuery.setParameter("brand", getAllBrands());
        logger.info("Getting brands...", getAllBrands());
        return (List<String>) typedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public Shoe findShoeById(int shoeId) {
        logger.info("Shoe by id: " + shoeId);
        Shoe shoe = em.find(Shoe.class, shoeId);
        return shoe;
    }
}
