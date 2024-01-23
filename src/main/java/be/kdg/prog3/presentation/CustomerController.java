package be.kdg.prog3.presentation;

import be.kdg.prog3.domain.Customer;
import be.kdg.prog3.domain.Gender;
import be.kdg.prog3.domain.Purchase;
import be.kdg.prog3.presentation.viewmodels.CustomerViewModel;
import be.kdg.prog3.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController extends SessionController {
    private final CustomerService customerService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public String showCustomers(Model model, HttpSession session) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        logger.info("Customers: {}", customers);
        updatePageVisitHistory("customers", session);
        return "customers";
    }

    @GetMapping("/customers/customerdetails")
    public String showCustomerDetails(@RequestParam(value = "customerId") int customerId, Model model, HttpSession session) {
        logger.info("Displaying customer details for customer id: {}... " , customerId);
        Customer customer = customerService.customerById(customerId);
        List<Purchase> purchases = customerService.getPurchaseByCustomer(customerId);
        logger.info("Customer: {}", customer);
        logger.info("Purchases: {}", purchases);
        model.addAttribute("customer", customer);
        model.addAttribute("purchases", purchases);

        updatePageVisitHistory("customerdetails", session);
        return "customerdetails";
    }

    @GetMapping("/customers/customersEmailNotContaining")
    public String showCustomersByEmailNotContaining(@RequestParam(value = "email") String email, Model model, HttpSession session) {
        logger.info("Displaying customers by email not containing..." + email);
        List<Customer> customers = customerService.findAllByEmailIsNotContaining(email);
        logger.info("Customers: {}", customers);
        model.addAttribute("customers", customers);
        updatePageVisitHistory("customersByPurchases", session);
        return "customers";
    }

    @GetMapping("/customers/customersLastNameStartingWith")
    public String showCustomersByLastNameStartingWith(@RequestParam(value = "lastname")String lastname, Model model, HttpSession session) {
        logger.info("Displaying customers by last name starting with...");
        List<Customer> customers = customerService.findAllByLastNameStartingWith(lastname);
        logger.info("Customers: {}", customers);
        model.addAttribute("customers", customers);
        updatePageVisitHistory("customersByPurchases", session);
        return "customers";
    }


    @PostMapping("/addCustomer")
    public String addCustomer(@Valid @ModelAttribute("customerViewModel") CustomerViewModel customerViewModel,
                              BindingResult result ) {
        logger.info("getting the customer form fields ...");
        if (result.hasErrors()) {
            result.getAllErrors().forEach(e-> logger.warn(e.toString()));
            return "add-customer";
        } else {
            logger.debug("Adding a customer: " + customerViewModel);
            Customer customer = new Customer(
                    customerViewModel.getFirstName(),
                    customerViewModel.getLastName(),
                    customerViewModel.getEmail(),
                    customerViewModel.getPhoneNumber(),
                    customerViewModel.getDateOfBirth(),
                    customerViewModel.getGender()
            );

            customerService.add(customer);
            return "redirect:customers";
        }
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam int customerId) {
        logger.info("Deleting customer with id {} ", customerId);
        customerService.deleteCustomer(customerId);
        return "redirect:customers";
    }

    @GetMapping("/addCustomer")
    public String addCustomerForm(Model model, HttpSession session) {
        logger.info("Customer form:");
        model.addAttribute("genders", Gender.values());
        model.addAttribute("customerViewModel", new CustomerViewModel());
        updatePageVisitHistory("addCustomer", session);
        return "add-customer";
    }

    @GetMapping("/sessionhistory")
    public String showSessionHistory(Model model, HttpSession session) {
        logger.info("Displaying session history...");
        List<String> pageVisits = (List<String>) session.getAttribute("pageVisits");
        model.addAttribute("pageVisits", pageVisits);
        return "sessionhistory";
    }


}
