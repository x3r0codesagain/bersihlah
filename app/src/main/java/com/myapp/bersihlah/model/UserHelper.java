package com.myapp.bersihlah.model;

public class UserHelper {
    String username;
    String email;
    String phoneNo;
    String password;





    public UserHelper(String username, String email, String phoneNo, String password) {
        this.username = username;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserHelper() {

    }

}
