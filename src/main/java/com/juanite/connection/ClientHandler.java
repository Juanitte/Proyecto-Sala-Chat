package com.juanite.connection;

import com.juanite.model.domain.Message;
import com.juanite.model.domain.Room;
import com.juanite.model.domain.User;
import com.juanite.util.AppData;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.juanite.connection.ChatServer.broadcast;
import static com.juanite.connection.ChatServer.clients;

public class ClientHandler implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private User user;
    private Room room;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public OutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public InputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ClientHandler() {

    }
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            user = (User) in.readObject();

            synchronized (clients) {
                while (clients.containsKey(user)) {
                    out.writeObject(false);
                    out.flush();
                    user = (User) in.readObject();
                }
                clients.put(user, out);
            }

            out.writeObject("Welcome, " + user.getUsername() + "!");
            out.flush();
            broadcast(new Message("User " + user.getUsername() + " has joined the chat.", LocalDateTime.now(), new User("Server"), new Room()));

            // Leer y reenviar los mensajes del cliente
            String message;
            while ((message = (String) in.readObject()) != null) {
                broadcast(new Message(user.getUsername() + ": " + message, LocalDateTime.now(), new User("Server"), new Room()));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (user != null) {
                //removeClient(nickname);
                try {
                    broadcast(new Message(user.getUsername() + " has left the chat.", LocalDateTime.now(), new User("Server"), new Room()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void joinRoom(Room room) {
        /*
        synchronized (ChatServer.getRooms()) {
            ChatServer.getClientRooms().put(user, room);
            ChatServer.getRooms().computeIfAbsent(room, k -> new HashSet<>()).add(user);
        }
         */
    }

    private void leaveRoom(Room room) {
        /*
        synchronized (ChatServer.getRooms()) {
            Set<User> roomMembers = ChatServer.getRooms().get(room);
            if (roomMembers != null) {
                roomMembers.remove(user);
                if (roomMembers.isEmpty()) {
                    ChatServer.getRooms().remove(room);
                }
            }
            ChatServer.getClientRooms().remove(user);
        }

         */
    }
}
