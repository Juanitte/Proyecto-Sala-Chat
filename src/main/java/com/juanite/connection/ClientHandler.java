package com.juanite.connection;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import static com.juanite.connection.ChatServer.broadcast;
import static com.juanite.connection.ChatServer.clients;

public class ClientHandler implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String nickname;
    private String room;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
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

            // Obtener el nombre de usuario del cliente y agregarlo a la lista de clientes
            out.writeObject("Welcome to the Chat Server. Please enter your nickname:");
            out.flush();
            nickname = (String) in.readObject();

            synchronized (clients) {
                while (clients.containsKey(nickname)) {
                    out.writeObject("Nickname already in use. Please choose another:");
                    out.flush();
                    nickname = (String) in.readObject();
                }
                clients.put(nickname, out);
            }

            out.writeObject("Welcome, " + nickname + "!");
            out.flush();
            broadcast("User " + nickname + " has joined the chat.");

            // Leer y reenviar los mensajes del cliente
            String message;
            while ((message = (String) in.readObject()) != null) {
                broadcast(nickname + ": " + message);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (nickname != null) {
                //removeClient(nickname);
                try {
                    broadcast(nickname + " has left the chat.");
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

    private void joinRoom(String room) {
        synchronized (ChatServer.getRooms()) {
            ChatServer.getClientRooms().put(nickname, room);
            ChatServer.getRooms().computeIfAbsent(room, k -> new HashSet<>()).add(nickname);
        }
    }

    private void leaveRoom(String room) {
        synchronized (ChatServer.getRooms()) {
            Set<String> roomMembers = ChatServer.getRooms().get(room);
            if (roomMembers != null) {
                roomMembers.remove(nickname);
                if (roomMembers.isEmpty()) {
                    ChatServer.getRooms().remove(room);
                }
            }
            ChatServer.getClientRooms().remove(nickname);
        }
    }
}
