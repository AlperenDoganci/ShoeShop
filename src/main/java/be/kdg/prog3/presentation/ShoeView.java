package be.kdg.prog3.presentation;

import be.kdg.prog3.domain.Customer;
import be.kdg.prog3.domain.Gender;
import be.kdg.prog3.service.CustomerService;
import be.kdg.prog3.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Scanner;

@Component
public class ShoeView {
    private final ShoeService shoeService;
    private final CustomerService customerService;

    private Scanner keyboard = new Scanner(System.in);

    @Autowired
    public ShoeView(ShoeService shoeService, CustomerService customerService) {
        this.shoeService = shoeService;
        this.customerService = customerService;
    }


    public void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.print("""
                    What would you like to do?
                    1) Show all customers
                    2) Filter customers by gender
                    3) Show all the shoes
                    4) Show all the brands
                    0) Exit
                    
                    Please enter the number of your choice: """);
            int choice = keyboard.nextInt();
            keyboard.nextLine();
            switch (choice) {
                case 1 -> showAllCustomers();
                case 2 -> filterCustomersByGender();
                case 3 -> showAllTheShoes();
                case 4 -> showAllTheBrands();
                case 0 -> exit = true;
                default -> System.out.println("Please enter a valid choice");
            }
        }
    }

    private void showAllCustomers() {
        System.out.println(customerService.getAllCustomers());
        System.out.println("Writing customers to Json");
        customerService.writeCustomerToJson(customerService.getAllCustomers());
    }

    private void filterCustomersByGender() {
        System.out.println("Enter a gender M or F");
        String gender = keyboard.nextLine();
        Collection<Customer> customersByGender = customerService.getAllCustomersByGender(Gender.valueOf(gender));
        System.out.println(customersByGender);
    }

    private void showAllTheShoes() {
        System.out.println(shoeService.getAll());
        System.out.println("Writing shoes to Json");
        shoeService.writeShoesToJson(shoeService.getAll());
    }

  private void showAllTheBrands() {
      System.out.println("All the brands: ");
      System.out.println(shoeService.getAllBrands());
    }
}
