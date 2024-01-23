package be.kdg.prog3.presentation.viewmodels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ShoeViewModel {
    @NotBlank(message = "brand should not be empty")
    private String brand;
    private String color;
    private int size;
    @Positive(message = "price should not be smaller than 0")
    private double price;

    public ShoeViewModel(String brand, String color, int size, double price) {
        this.brand = brand;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    public ShoeViewModel() {
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ShoeViewModel{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
