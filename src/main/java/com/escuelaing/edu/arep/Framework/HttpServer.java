package com.escuelaing.edu.arep.Framework;

import java.net.*;
import java.io.*;

import java.lang.reflect.Method;

import java.nio.charset.StandardCharsets;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class HttpServer {

    public static void run() throws IOException {
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
                if (!in.ready()) {
                    break;
                }
                if (inputLine.contains("GET")) {
                    String path = inputLine.split(" ")[1];
                    if (path.contains("apps/")){
                        String[] params = null;
                        String htmlPath;
                        if (path.contains("?")){
                            int i = path.indexOf("?");
                            params = path.substring(i + 1).split("=");
                            htmlPath = path.substring(path.indexOf("apps/"), i);
                        } else {
                            htmlPath = path.substring(path.indexOf("apps/"));
                        }
                        if (URLHandler.containsKey(s)) {
                            if (param == null)
                                String response = URLHandler.get(htmlPath).process();
                            else
                                String response = URLHandler.get(htmlPath).process(new String[] { param[1] });
                            getRequest("202 OK", "text/html", out, response);
                        } else {
                            getRequest("404 Not Found", "text/html", out, "Not Found");
                        }
                    } else {
                        if(path.equals("/")){
                            LoaderFile.getFile(out, "/index.html", clientSocket);
                        }
                        else if (path.contains(".")) {
                            LoaderFile.getFile(out, path, clientSocket);
                        } else {
                            outputLine = "<!DOCTYPE html>" + "<html>" + "<head>" + "<metacharset=\"UTF-8\">"
                                    + "<title>Title of the document</title>\n" + "</head>" + "<body>"
                                    + "My Web Framework" + "</body>" + "</html>";
                            getRequest("200 OK", "text/html", out, outputLine);
                        }

                    }
                }
            }
            out.write(outputLine);

            out.close();
            in.close();

            clientSocket.close();
            serverSocket.close();
        }
    }

    private void getRequest(String httpStatus, String mimeType, String content) {
        out.write("HTTP/1.1 " + httpStatus + "\r\n");
        out.write("Content-Type: " + mimeType + "\r\n");
        out.write("\r\n");
        out.write(content);
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; // returns default port if heroku-port isn't set (i.e.on localhost)
    }
}