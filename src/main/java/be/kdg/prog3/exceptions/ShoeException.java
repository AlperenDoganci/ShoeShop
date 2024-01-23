package be.kdg.prog3.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Brand not Found!")
public class ShoeException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ShoeException(String message) {
        super(message);
        logger.info("ShoeException created...");
    }

}
