package com.javaweb.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotEmpty(message = "Vui lòng nhập vào tên sản phẩm")
    private String name;

    @NotNull
    @Min(value = 1000, message = "Vui lòng nhập giá lớn hơn 1000 VNĐ")
    private double price;

    private String image;

    @NotNull
    @NotEmpty(message = "Vui lòng nhập thông tin chi tiết sản phẩm")
    @Column(columnDefinition = "MEDIUMTEXT")
    private String detailDesc;

    @NotNull
    @NotEmpty(message = "Vui lòng nhập mô tả ngắn chi tiết sản phẩm")
    private String shortDesc;

    @NotNull(message = "Vui lòng nhập số lượng sản phẩm")
    @Min(value = 1, message = "Tối thiểu có 1 sản phẩm")
    private long quantity;

    private long sold;

    private String factory;

    public String target;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getSold() {
        return sold;
    }

    public void setSold(long sold) {
        this.sold = sold;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

}
