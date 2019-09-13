package com.escuelaing.edu.arep.Server;

import com.escuelaing.edu.arep.Annotations.Web;

public class WebServer {

    @Web
    public static String Sample() {
        return "<h1>Sample Page</h1>";
    }

    @Web("template")
    public static String textTemplate(String p) {
        return "<h1>I could be an awesome template</h1><p>with the text from the query string: " + p + "</p>";
    }
}