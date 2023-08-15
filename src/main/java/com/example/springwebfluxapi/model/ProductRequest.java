package com.example.springwebfluxapi.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductRequest {

    @NotBlank(message = "Required")
    @Length(max = 50, message = "More than {max} characters")
    private String name;

    @NotBlank(message = "Required")
    @Length(max = 50, message = "More than {max} characters")
    private String description;

    @NotNull
    private Integer category_id;

    @NotNull
    private Integer price;

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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
