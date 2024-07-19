package org.alexsoft;

import org.alexsoft.service.AtmService;


public class Main {

    public static void main(String[] args) {
        var atm = new AtmService();
        atm.start();
    }

}