package com.example.shop_bills_javafx;

public class order_product {
    private Integer id;
    private Integer productId;
    private String productName;
    private String type;
    private Double price;
    private Integer quantity;

    public order_product(Integer id, Integer productId, String name, String type, Double price, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId(){
        return id;
    }
    public Integer getProductId(){
        return productId;
    }
    public String getName(){
        return productName;
    }
    public String getType(){
        return type;
    }
    public Double getPrice(){
        return price;
    }
    public Integer getQuantity(){
        return quantity;
    }
}