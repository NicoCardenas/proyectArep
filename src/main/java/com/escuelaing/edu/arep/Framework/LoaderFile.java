package com.escuelaing.edu.arep;

import java.io.File;
import java.io.InputStream;

public class LoaderFile {

    public static void getFile(String path) {
        InputStream in = LoaderFile.class.getResourceAsStream(path);
        if ( in == null )
            throw new Exception("resource not found: " + path);
    }
}