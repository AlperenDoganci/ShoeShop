package be.kdg.prog3.presentation;

import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/index")
    public String indexPage() {
        logger.info("Displaying index page...");
        return "index";
    }


}
