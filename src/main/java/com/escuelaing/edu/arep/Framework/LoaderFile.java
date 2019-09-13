package com.escuelaing.edu.arep.Framework;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.escuelaing.edu.arep.Framework.HttpServer;

public class LoaderFile {

    public void readFile(String path, PrintWriter out, HttpServer hs, String httpStatus, String mimeType, Socket socket) {

        try {
            File file;
            BufferedReader bufferedReader;
            BufferedImage image;
            String line, outputline = "";

            if (path.contains(".html")) {
                file = new File(getClass().getClassLoader().getResource(path).getFile());
                FileReader reader = new FileReader(file);
                bufferedReader = new BufferedReader(reader);
                while ((line = bufferedReader.readLine()) != null) {
                    outputline += line;
                }
                hs.getRequest(httpStatus, mimeType, outputline, out);
                bufferedReader.close();
            } else {
                System.out.println(getClass().getResource(path));
                String type;
                if (path.contains(".jpg"))
                    type = "jpg";
                else if (path.contains(".jpe"))
                    type = "jpe";
                else if (path.contains(".jpeg"))
                    type = "jpeg";
                else 
                    type = "png";
                image = ImageIO.read(getClass().getResource(path));
                hs.getRequest(httpStatus, mimeType, out);
                ImageIO.write(image, type, socket.getOutputStream());
            }
        } catch (Exception e) {
            String outputline = "<!DOCTYPE html>" + "<html>" + "<head>" + "<metacharset=\"UTF-8\">"
            + "<title>Title of the document</title>\n" + "</head>" + "<body>"
            + "<center><h1>File Not Found</h1></center>" + "</body>" + "</html>";
            hs.getRequest("404 Not Found", mimeType, outputline, out);
            e.printStackTrace();
        }
    }
}