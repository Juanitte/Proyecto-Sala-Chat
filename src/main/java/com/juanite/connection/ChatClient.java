package com.juanite.connection;

import com.juanite.model.domain.Message;
import com.juanite.model.domain.Room;
import com.juanite.model.domain.User;
import com.juanite.util.AppData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChatClient {
    private static final String SERVER_ADDRESS = "172.16.16.115";
    private static final int SERVER_PORT = 8080;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public ChatClient() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(this::updateRooms, 0, 10, TimeUnit.SECONDS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateRooms() {

    }


    public void joinRoom(User user, Room room) {

    }


    public void sendMessage(String messageContent) throws IOException {
        Message message = new Message(messageContent, LocalDateTime.now(), AppData.getCurrentUser(), AppData.getCurrentRoom());
        out.writeObject(message);
        out.flush();
    }

    public void disconnectFromServer() throws IOException {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (out != null) {
            out.close();
        }
    }
}
