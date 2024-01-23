package be.kdg.prog3.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shoes")
public class Shoe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shoe_id")
    private int shoeId;
    @Column(name = "brand")
    private String brand;
    @Column(name = "size")
    private double size;
    @Column(name = "color")
    private String color;
    @Column(name = "price")
    private double price;

    @ManyToMany(mappedBy = "shoes", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.REMOVE})
    private /*transient*/ List<Customer> customers = new ArrayList<>();

    @ManyToMany(mappedBy = "shoeList", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Purchase> purchases = new ArrayList<>();

    public Shoe(String brand, double size, String color, double price) {
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.price = price;
    }

    public Shoe(int shoeId, String brand, double size, String color, double price) {
        this.shoeId = shoeId;
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.price = price;
    }
    public Shoe() {

    }

    public void addCustomer(Customer customer) {
        if (customers==null) customers = new ArrayList<>();
        customers.add(customer);
    }

    public int getShoeId() {
        return shoeId;
    }

    public String getBrand() {
        return brand;
    }

    public double getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public void setShoeId(int shoeId) {
        this.shoeId = shoeId;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }
    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    @Override
    public String toString() {
        return "Shoe{" +
                " shoeId=" + shoeId +
                ", brand='" + brand + '\'' +
                ", size=" + size +
                ", color='" + color + '\'' +
                ", price=$" + price +
                '}';
    }
}