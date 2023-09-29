package com.juanite.util;

import com.juanite.connection.ChatClient;
import com.juanite.connection.ClientHandler;
import com.juanite.model.domain.Room;
import com.juanite.model.domain.Message;
import com.juanite.model.domain.User;

import java.net.Socket;

public class AppData {

    private static User currentUser;
    private static Room currentRoom;
    private static Message currentMessage;
    private static ChatClient cc = new ChatClient();
    private static ClientHandler ch = new ClientHandler();

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        AppData.currentUser = currentUser;
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentRoom(Room currentRoom) {
        AppData.currentRoom = currentRoom;
    }

    public static Message getCurrentMessage() {
        return currentMessage;
    }

    public static void setCurrentMessage(Message currentMessage) {
        AppData.currentMessage = currentMessage;
    }
}
