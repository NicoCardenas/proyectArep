package com.escuelaing.edu.arep;

import java.net.*;
import java.io.*;

public class HttpServer {

    public static void main(String[] args) throws IOException {
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
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            try {
                File file = new HtmlFile().main("index.html");
                System.out.println(file);
            } catch (Exception e) {
                System.out.println(e);
            }
            

            outputLine = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n" + "<!DOCTYPE html>\n"
                    + "<html>\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n" + "<title>Title of the document</title>\n"
                    + "</head>\n" + "<body>\n" + "<h1>Mi propio mensaje</h1>\n" + "</body>\n" + "</html>\n";

            out.write(outputLine);

            out.close();

            in.close();

            clientSocket.close();

            serverSocket.close();
        }
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; // returns default port if heroku-port isn't set (i.e.on localhost)
    }

}