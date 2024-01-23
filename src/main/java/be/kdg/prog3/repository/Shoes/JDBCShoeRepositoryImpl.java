package be.kdg.prog3.repository.Shoes;

import be.kdg.prog3.domain.Shoe;
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
public class JDBCShoeRepositoryImpl implements ShoeRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert shoeInserter;
    private final static Logger logger = LoggerFactory.getLogger(JDBCShoeRepositoryImpl.class);

    public JDBCShoeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        logger.info("Creating JDBCShoeRepositoryImpl...");
        this.jdbcTemplate = jdbcTemplate;
        this.shoeInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("shoes")
                .usingGeneratedKeyColumns("shoe_id");
    }

    public static Shoe mapRow(ResultSet rs, int rowid) throws SQLException {
        logger.info("Mapping row {} to shoe...", rowid);
        return new Shoe(rs.getInt("shoe_id"),
                rs.getString("brand"),
                rs.getInt("size"),
                rs.getString("color"),
                rs.getDouble("price"));
    }
    @Override
    public Shoe createShoe(Shoe shoe) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("brand", shoe.getBrand());
        parameters.put("size", shoe.getSize());
        parameters.put("color", shoe.getColor());
        parameters.put("price", shoe.getPrice());
        shoe.setShoeId(shoeInserter.executeAndReturnKey(parameters).intValue());
        logger.info("Creating shoe {}", shoe);
        return shoe;
    }

    @Override
    public void updateShoe(Shoe shoe) {
        logger.info("Updating shoe with id {}...", shoe.getShoeId());
        jdbcTemplate.update("UPDATE shoes SET brand=?, size=?, color=?, price=? WHERE shoe_id=?",
                shoe.getBrand(), shoe.getSize(), shoe.getColor(), shoe.getPrice(), shoe.getShoeId());
    }

    @Override
    public List<Shoe> findAllShoes() {
        logger.info("Reading shoes...");
        return jdbcTemplate.query(
                "SELECT * FROM shoes", JDBCShoeRepositoryImpl::mapRow);
    }

    @Override
    public Shoe findShoeById(int shoeId) {
        Shoe shoe = jdbcTemplate.queryForObject(
                "SELECT * FROM shoes WHERE shoe_id=?",
                new Object[]{shoeId},
                JDBCShoeRepositoryImpl::mapRow);
        assert shoe != null;
        logger.info("Reading shoe with id {}", shoe.getShoeId());
        return shoe;
    }

    @Override
    public List<String> getAllBrands() {
        logger.info("Reading brands...");
        return jdbcTemplate.queryForList("SELECT DISTINCT brand FROM shoes", String.class);
    }

    @Override
    public void deleteShoe(int shoeId) {
        logger.info("Deleting shoe with id {}", shoeId);
        jdbcTemplate.update("DELETE FROM shoes WHERE shoe_id=?", shoeId);
    }
}
