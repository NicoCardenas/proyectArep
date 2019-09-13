package com.escuelaing.edu.arep.Server;

import com.escuelaing.edu.arep.Annotations.Web;

public class WebServer {

    @Web
    public static String Sample() {
        return "<h1>Sample Page</h1>";
    }

    @Web("example")
    public static String textTemplate(String p) {
        return "<p>texto: " + p + "</p>";
    }
}