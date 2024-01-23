package be.kdg.prog3.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private int purchaseId;
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;
    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(
            name = "purchase_shoe",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "shoe_id"))
    private List<Shoe> shoeList = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Purchase(int purchaseId, LocalDate purchaseDate, double totalPrice, PaymentMethod paymentMethod) {
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
    }

    public Purchase() {

    }

    public void addShoe(Shoe shoe) {
        shoeList.add(shoe);
    }
    public int getPurchaseId() {
        return purchaseId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public List<Shoe> getShoeList() {
        return shoeList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getCustomer_id() {
        return customer != null ? customer.getCustomerId() : null;
    }

    public void setCustomer_id(int customer_id) {
        this.customer = new Customer(customer_id);
    }
    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setShoeList(List<Shoe> shoeList) {
        this.shoeList = shoeList;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseId=" + purchaseId +
                ", purchaseDate=" + purchaseDate +
                ", totalPrice=" + totalPrice +
                ", paymentMethod=" + paymentMethod +
                ", customer_id=" + getCustomer_id() +
                '}';
    }
}
