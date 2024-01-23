package be.kdg.prog3.presentation;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;


import java.util.*;

@Controller
public class SessionController {

    protected void updatePageVisitHistory(String pageName, HttpSession session) {
        List<String> pageVisits = (List<String>) session.getAttribute("pageVisits");
        if (pageVisits == null) {
            pageVisits = new ArrayList<>();
        }

        pageVisits.add(pageName + " - " + new Date());

        session.setAttribute("pageVisits", pageVisits);
    }

}
