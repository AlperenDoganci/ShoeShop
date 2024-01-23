package be.kdg.prog3.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseException extends RuntimeException {
    private final Logger logger = LoggerFactory.getLogger(DatabaseException.class);

    public DatabaseException(String message) {
        super(message);
        logger.debug("DatabaseException created...");
    }
}
