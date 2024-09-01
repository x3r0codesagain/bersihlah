package com.myapp.bersihlah.model;

public class OrderHelper {
    String orderID;
    String phoneNo;
    String address;
    String hour;
    String minute;
    String price;
    String paymentMethod;
    String type;
    String username;
    String date;

    public OrderHelper(String username, String type, String phoneNo, String orderID, String address, String hour, String minute, String price, String paymentMethod, String date) {
        this.orderID = orderID;
        this.address = address;
        this.hour = hour;
        this.minute = minute;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.type = type;
        this.username = username;
        this.phoneNo = phoneNo;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
