package com.escuelaing.edu.arep;

import java.io.File;

public static class LoaderFile {

    public void getFile(String path) {
        LoaderFile.class.getResourceAsStream(path);
        if ( in == null )
            throw new Exception("resource not found: " + respath);
    }
}