package com.mir.passpocket;

public class AccountModel {
    private String name;
    private String email;
    private String password;
    private String url;
    private String category;
    private String modified;

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

    public AccountModel(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }

    public AccountModel(String name, String email, String password, String url, String category, String modified) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.url = url;
        this.category = category;
        this.modified = modified;
    }
}
