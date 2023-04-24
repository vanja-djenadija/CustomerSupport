package com.example.customersupport;

import com.example.customersupport.services.AuthService;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(new AuthService().signIn("support", "support"));
    }
}