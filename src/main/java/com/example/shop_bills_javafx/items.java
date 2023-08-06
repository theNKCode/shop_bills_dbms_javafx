package com.example.shop_bills_javafx;

public class items {
    private String product_id;
    private String product_name;
    private String type;
    private double price;
    private String status;

    public items(String product_id, String product_name, String type, Double price, String status){
        this.product_id = product_id;
        this.product_name = product_name;
        this.type = type;
        this.price = price;
        this.status = status;
    }

    public String getProductId(){
        return product_id;
    }
    public String getName(){
        return product_name;
    }
    public String getType(){
        return type;
    }
    public Double getPrice(){
        return price;
    }
    public String getStatus(){
        return status;
    }
}
