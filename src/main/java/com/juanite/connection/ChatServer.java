package com.juanite.connection;

import com.juanite.model.domain.Message;
import com.juanite.model.domain.Room;
import com.juanite.model.domain.User;

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

    static Map<User, ObjectOutputStream> clients = new HashMap<>();
    private static Map<User, Room> clientRooms = new HashMap<>();
    private static Map<Room, Set<User>> rooms = new HashMap<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(MAX_CLIENTS);

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

    static void broadcast(Message message) throws IOException {
        synchronized (clients) {
            for (ObjectOutputStream client : clients.values()) {
                client.writeObject(message);
                client.flush();
            }
        }
    }

    static void broadcastToRoom(Room room, Message message) throws IOException {
        synchronized (rooms) {
            Set<User> roomMembers = rooms.get(room);
            if (roomMembers != null) {
                for (User member : roomMembers) {
                    ObjectOutputStream client = clients.get(member);
                    if (client != null) {
                        client.writeObject(message);
                        client.flush();
                    }
                }
            }
        }
    }

    static void updateRooms() {
        synchronized (clients) {
            for (ObjectOutputStream client : clients.values()) {

            }
        }
    }
}
