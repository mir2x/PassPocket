package com.mir.passpocket;

public class AccountModel {
    private int id;
    private String name;

    private String email;
    private String password;
    private String url;
    private String category;
    private String modified;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public String getModified() {
        return modified;
    }

    public AccountModel() {
        super();
    }

    public AccountModel(int id, String name, String email, String password, String url, String category, String modified) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.url = url;
        this.category = category;
        this.modified = modified;
    }
}
