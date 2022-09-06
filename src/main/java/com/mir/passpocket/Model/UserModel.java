package com.mir.passpocket.Model;

public class UserModel {
    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private byte admin;

    public int getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {return userPassword;}

    public byte getUserAdmin() {
        return admin;
    }


}
