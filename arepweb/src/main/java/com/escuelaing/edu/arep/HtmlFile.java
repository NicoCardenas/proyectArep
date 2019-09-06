package com.escuelaing.edu.arep;

import java.io.File;

public class HtmlFile {

    public static File main(String args) {
        HtmlFile html = new HtmlFile();
        return html.getHtml(args);
    }

    public File getHtml(String path) {
        File file = new File(getClass().getClassLoader().getResource(path).getFile());
        System.out.println("ruta del archivo"+file.getAbsolutePath());
        return file;
    }
}