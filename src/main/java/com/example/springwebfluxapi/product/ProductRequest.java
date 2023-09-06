package com.example.springwebfluxapi.product;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ProductRequest {

    @NotBlank(message = "Required")
    @Length(max = 50, message = "More than {max} characters")
    private String name;

    @NotBlank(message = "Required")
    @Length(max = 50, message = "More than {max} characters")
    private String description;

    @NotNull
    private Integer price;

    @NotNull
    private Integer categoryId;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRequest that = (ProductRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(categoryId, that.categoryId) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, categoryId, price);
    }
}
