package com.thanglv.hibernate.controller.vm;

import java.io.Serializable;

/**
 * @author thanglv on 4/27/2021
 * @project hibernate
 */
public class LoginVM implements Serializable {

    private String username;

    private String password;

    public LoginVM() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
