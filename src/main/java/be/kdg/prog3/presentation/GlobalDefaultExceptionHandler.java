package be.kdg.prog3.presentation;

import be.kdg.prog3.exceptions.DatabaseException;
import be.kdg.prog3.exceptions.ShoeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DatabaseException.class)
    public String handleDatabaseException(DatabaseException exception, Model model) {
        logger.debug("DatabaseException created...");
        model.addAttribute("Database error", exception.getMessage());
        return "databaseerror";
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(ShoeException.class)
    public String handleShoeError(ShoeException exception, Model model) {
        logger.debug("Shoe exception created...");
        model.addAttribute("Shoe brand error", exception.getMessage());
        return "shoeerror";
    }
}
