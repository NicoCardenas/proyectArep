package com.escuelaing.edu.arep.Framework;

import java.io.IOException;

public class Driver {

    public static void main(String[] args) {
        HttpServer httpserver = new HttpServer();
        ValidatorWeb VW = new ValidatorWeb();
        VW.initializeWeb();
        try {
            httpserver.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}