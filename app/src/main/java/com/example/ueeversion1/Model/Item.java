package com.example.ueeversion1.Model;


public class Item {
    private String pid;
    private String ProductName;
    private String ProductCategory;
    private String ProductType;
    private String available;
    private String Price;
    private String Stock;
    private String description;
    private String Date;
    private String Time;
    private String Image;
    private String status;
    private String currentDate;
    private String receptions;

    public Item() {
    }

    public Item(String pid, String productName, String productCategory, String productType, String available, String price, String stock, String description, String date, String time, String image, String status, String currentDate, String receptions) {
        this.pid = pid;
        ProductName = productName;
        ProductCategory = productCategory;
        ProductType = productType;
        this.available = available;
        Price = price;
        Stock = stock;
        this.description = description;
        Date = date;
        Time = time;
        Image = image;
        this.status = status;
        this.currentDate = currentDate;
        this.receptions = receptions;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        ProductCategory = productCategory;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getReceptions() {
        return receptions;
    }

    public void setReceptions(String receptions) {
        this.receptions = receptions;
    }
}