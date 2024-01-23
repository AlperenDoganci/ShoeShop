package be.kdg.prog3.presentation;

import be.kdg.prog3.domain.Purchase;
import be.kdg.prog3.domain.Shoe;
import be.kdg.prog3.exceptions.DatabaseException;
import be.kdg.prog3.exceptions.ShoeException;
import be.kdg.prog3.presentation.viewmodels.ShoeViewModel;
import be.kdg.prog3.service.ShoeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class ShoeController extends SessionController {
    private final ShoeService shoeService;
    private static final Logger logger = LoggerFactory.getLogger(ShoeController.class);

    public ShoeController(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

    @GetMapping("/shoes")
    private String showShoes(Model model, HttpSession session) {
        List<Shoe> shoes = shoeService.getAll();
        model.addAttribute("shoes",shoes);
        logger.info("Shoes: {}", shoes);
        updatePageVisitHistory("shoes", session);
        return "shoes";
    }

    @GetMapping("/shoes/shoedetails")
    public String showShoeDetails(@RequestParam(value = "shoeId") int shoeId, Model model, HttpSession session) {
        logger.info("Displaying shoe details ... " + shoeId);
        Shoe shoe = shoeService.shoeById(shoeId);
        List<Purchase> purchases = shoeService.getPurchaseByShoe(shoeId);
        logger.info("Shoe: {}", shoe);
//        logger.info("Purchases: {}", purchases);
        model.addAttribute("shoe", shoe);
        model.addAttribute("purchases", purchases);
        updatePageVisitHistory("shoedetails", session);
        return "shoedetails";
    }

    //http://localhost:8080/shoes/shoesByBrand?brand=Adidas
    @GetMapping("/shoes/shoesByBrand")
    public String showShoesByBrand(@RequestParam(value = "brand") String brand, Model model, HttpSession session) {
        logger.debug("Displaying shoes by brand ..." + brand);
        try {
            List<Shoe> shoes = shoeService.getAllByBrand(brand);
            model.addAttribute("shoes", shoes);
            updatePageVisitHistory("shoesByBrand", session);
            return "shoes";
        } catch (ShoeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "shoeerror";
        }
    }

    //http://localhost:8080/shoes/shoesByPriceGreaterThan?price=110
    @GetMapping("/shoes/shoesByPriceGreaterThan")
    public String showShoesByPriceGreaterThan(@RequestParam(value = "price") double price, Model model, HttpSession session) {
        logger.debug("Displaying shoes by price ..." + price);
        List<Shoe> shoes = shoeService.findAllShoesByPriceGreaterThan(price);
        model.addAttribute("shoes", shoes);
        updatePageVisitHistory("shoesByPrice", session);
        return "shoes";
    }

    //http://localhost:8080/shoes/shoesByPriceLessThan?price=110
    @GetMapping("/shoes/shoesByPriceLessThan")
    public String showShoesByPriceLessThan(@RequestParam(value = "price") double price, Model model, HttpSession session) {
        logger.debug("Displaying shoes by price ..." + price);
        List<Shoe> shoes = shoeService.findAllShoesByPriceLessThan(price);
        model.addAttribute("shoes", shoes);
        updatePageVisitHistory("shoesByPrice", session);
        return "shoes";
    }



    @PostMapping("/addShoe")
    public String addShoe(@Valid @ModelAttribute("shoeViewModel") ShoeViewModel shoeViewModel, BindingResult result, Model model) {
        logger.info("Adding a shoe: " + shoeViewModel);
        if (result.hasErrors()) {
            result.getAllErrors().forEach(e-> logger.warn(e.toString()));
            return "add-shoe";
        } else {
            try{

                logger.info("getting the shoe form fields ...");
                Shoe shoe = new Shoe(
                        shoeViewModel.getBrand(),
                        shoeViewModel.getSize(),
                        shoeViewModel.getColor(),
                        shoeViewModel.getPrice()
                );
                shoeService.addShoe(shoe);
                return "redirect:shoes";
            } catch (DatabaseException exception) {
                model.addAttribute("errorMessage", "Database error: " + exception.getMessage());
                return "databaseerror";
            }
        }
    }

    @GetMapping("/deleteShoe")
    public String deleteShoe(@RequestParam int shoeId) {
        logger.info("Deleting shoe with id {} ", shoeId);
        shoeService.deleteShoe(shoeId);
        return "redirect:shoes";
    }

    @GetMapping("/addShoe")
    public String addShoeForm(Model model, HttpSession session) {
        logger.debug("Showing the add shoe form");
        model.addAttribute("shoeViewModel", new ShoeViewModel());

        model.addAttribute("brands", shoeService.getAllBrands());
        updatePageVisitHistory("addShoe", session);
        return "add-shoe";
    }

}
