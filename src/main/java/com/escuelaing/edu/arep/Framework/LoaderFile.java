package com.escuelaing.edu.arep.Framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import com.escuelaing.edu.arep.Framework.HttpServer;

public class LoaderFile {
    private static String STATICFILES="/src/main/resources/";

    public void readFile(String path, PrintWriter out, HttpServer hs, String httpStatus, String mimeType) {

        try {
            File file = new File(getClass().getClassLoader().getResource(path).getFile());
            System.out.println(file.getAbsolutePath());
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line, outputline = "";
            while ((line = bufferedReader.readLine()) != null) {
                outputline += line;
            }
            hs.getRequest(httpStatus, mimeType, outputline, out);
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        /*String Absolutepath = System.getProperty("user.dir")+STATICFILES + path;
        String line, outputline = "";
        try {
            FileReader fileReader = new FileReader(Absolutepath);
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                out.write(line);
            }
            hs.getRequest(httpStatus, mimeType, outputline, out);
            bufferedReader.close();         
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {              
            ex.printStackTrace();
        }*/
    }
}