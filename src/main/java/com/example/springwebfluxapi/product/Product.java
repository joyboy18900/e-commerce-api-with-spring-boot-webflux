package com.example.springwebfluxapi.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table("products")
public class Product implements Serializable {

    @Id
    private Integer id;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("price")
    private int price;

    @Column("category_id")
    private int category_id;

    @Column("create_date")
    private LocalDateTime create_date;

    @Column("update_date")
    private LocalDateTime update_date;

    public Product() {
    }

    public Product(Integer id, String name, String description, int category_id, int price, LocalDateTime create_date, LocalDateTime update_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category_id = category_id;
        this.price = price;
        this.create_date = create_date;
        this.update_date = update_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }

    public LocalDateTime getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(LocalDateTime update_date) {
        this.update_date = update_date;
    }
}
