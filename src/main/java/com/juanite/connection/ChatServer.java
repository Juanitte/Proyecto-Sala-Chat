package com.juanite.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ChatServer {
    private static final int PORT = 8080;
    private static final int MAX_CLIENTS = 100;

    static Map<String, ObjectOutputStream> clients = new HashMap<>();
    private static Map<String, String> clientRooms = new HashMap<>();
    private static Map<String, Set<String>> rooms = new HashMap<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(MAX_CLIENTS);

    public static Map<String, ObjectOutputStream> getClients() {
        return clients;
    }

    public static void setClients(Map<String, ObjectOutputStream> clients) {
        ChatServer.clients = clients;
    }

    public static Map<String, String> getClientRooms() {
        return clientRooms;
    }

    public static void setClientRooms(Map<String, String> clientRooms) {
        ChatServer.clientRooms = clientRooms;
    }

    public static Map<String, Set<String>> getRooms() {
        return rooms;
    }

    public static void setRooms(Map<String, Set<String>> rooms) {
        ChatServer.rooms = rooms;
    }

    public static ExecutorService getPool() {
        return pool;
    }

    public static void setPool(ExecutorService pool) {
        ChatServer.pool = pool;
    }

    public static void main(String[] args) {
        System.out.println("Chat Server is running...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                pool.execute(new ClientHandler(serverSocket.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void broadcast(String message) throws IOException {
        synchronized (clients) {
            for (ObjectOutputStream client : clients.values()) {
                client.flush();
            }
        }
    }

    static void broadcastToRoom(String room, String message) throws IOException {
        synchronized (rooms) {
            Set<String> roomMembers = rooms.get(room);
            if (roomMembers != null) {
                for (String member : roomMembers) {
                    ObjectOutputStream client = clients.get(member);
                    if (client != null) {
                        client.flush();
                    }
                }
            }
        }
        System.out.println("Mensaje recibido: " + message);
    }
}
