package com.escuelaing.edu.arep.Framework;

import java.net.*;
import java.awt.image.BufferedImage;
import java.io.*;

import java.lang.reflect.Method;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.escuelaing.edu.arep.Framework.LoaderFile;
import com.escuelaing.edu.arep.Framework.impl.MethodHandler;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class HttpServer {
    private static Map<String, Handler> URLHandler = new HashMap<String, Handler>();

    public void run() throws IOException {
        while (true) {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(getPort());
            } catch (IOException e) {
                System.err.println("Could not listen on port: " + getPort());
                System.exit(1);
            }
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (inputLine.contains("GET")) {
                    String path = inputLine.split(" ")[1];
                    if (path.contains("apps/")) {
                        String[] params = null;
                        String htmlPath;
                        if (path.contains("?")){
                            int i = path.indexOf("?");
                            params = path.substring(i + 1).split("=");
                            htmlPath = path.substring(path.indexOf("apps/"), i);
                        } else {
                            htmlPath = path.substring(path.indexOf("apps/"));
                        }
                        if (URLHandler.containsKey(htmlPath)) {
                            String response;
                            if (params == null)
                                response = URLHandler.get(htmlPath).process();
                            else
                                response = URLHandler.get(htmlPath).process(new String[] { params[1] });
                            getRequest("202 OK", "text/html", response, out);
                        } else {
                            getRequest("404 Not Found", "text/html", "Not Found", out);
                        }
                    } else {
                        LoaderFile file = new LoaderFile();
                        if(path.equals("/")){
                            try {
                                file.readFile("index.html", out, this, "200 OK", "text/html", clientSocket);
                            } catch (Exception e) {
                                System.err.println(e);
                            }
                        }
                        else if (path.contains(".")) {
                            String mineType;
                            if (path.contains(".png"))
                                mineType = "image/png";
                            else if (path.contains(".jpg") || path.contains(".jpeg") || path.contains(".jpe"))
                                mineType = "image/jpeg";
                            else
                                mineType = "text/html";
                            file.readFile(path, out, this, "200 OK", mineType, clientSocket);
                        } else {
                            outputLine = "<!DOCTYPE html>" + "<html>" + "<head>" + "<metacharset=\"UTF-8\">"
                                    + "<title>Title of the document</title>\n" + "</head>" + "<body>"
                                    + "<center><h1>File Not Found</h1></center>" + "</body>" + "</html>";
                            getRequest("200 OK", "text/html", outputLine, out);
                        }
                    }
                }

                if (!in.ready()) {
                    break;
                }
            }
            out.close();
            in.close();

            clientSocket.close();
            serverSocket.close();
        }
    }

    public static void getRequest(String httpStatus, String mimeType, String content, PrintWriter out) {
        out.write("HTTP/1.1 " + httpStatus + "\r\n");
        out.write("Content-Type: " + mimeType + "\r\n");
        out.write("\r\n");
        out.write(content);
    }

    public static void getRequest(String httpStatus, String mimeType, PrintWriter out) {
        out.write("HTTP/1.1 " + httpStatus + "\r\n");
        out.write("Content-Type: " + mimeType + "\r\n");
        out.write("\r\n");
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; // returns default port if heroku-port isn't set (i.e.on localhost)
    }

    public static void put(String path, MethodHandler methodHandler){
        URLHandler.put(path, methodHandler);
    }
}