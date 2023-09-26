package com.juanite.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private ServerSocket serverSocket;
    private final int port = 8080; // Puerto en el que escuchará el servidor

    public ChatServer() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("El servidor está escuchando en el puerto " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                // Crea un nuevo hilo o proceso para manejar la conexión entrante
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.start();
    }
}
