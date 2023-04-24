package com.example.customersupport.services;

import com.example.customersupport.beans.AdminBean;
import com.example.customersupport.dao.AdminDAO;

import java.io.Serializable;

public class AuthService implements Serializable {

    public AdminBean signIn(String username, String password) {
        return AdminDAO.selectAdmin(username, password);
    }
}