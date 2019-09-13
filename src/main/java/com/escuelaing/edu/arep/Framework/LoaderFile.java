package com.escuelaing.edu.arep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class LoaderFile {

    public void readFile(String path, PrintWriter out) {

        try {
            File file = new File(getClass().getClassLoader().getResource("index.html").getFile());
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                out.write(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        /*String line = null;
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                out.write(line);
            }
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