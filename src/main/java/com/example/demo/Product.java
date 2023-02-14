package com.example.demo;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    int id;
    int partNumber;
    String name;
    String description;
    Boolean isForSale;
    BigDecimal price;

    public Product(int id, int partNumber, String name, String description, Boolean isForSale, BigDecimal price) {
        this.id = id;
        this.partNumber = partNumber;
        this.name = name;
        this.description = description;
        this.isForSale = isForSale;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getForSale() {
        return isForSale;
    }

    public void setForSale(Boolean forSale) {
        isForSale = forSale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && partNumber == product.partNumber && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(isForSale, product.isForSale) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, partNumber, name, description, isForSale, price);
    }
}
