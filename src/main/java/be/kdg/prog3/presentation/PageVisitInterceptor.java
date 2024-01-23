package be.kdg.prog3.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PageVisitInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        List<String> pageVisits = (List<String>) session.getAttribute("pageVisits");
        if (pageVisits == null) {
            pageVisits = new ArrayList<>();
        }

        pageVisits.add(request.getRequestURI() + " - " + new Date());

        session.setAttribute("pageVisits", pageVisits);

        return true;
    }
}
