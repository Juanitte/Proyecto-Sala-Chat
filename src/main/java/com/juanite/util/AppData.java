package com.juanite.util;

import com.juanite.connection.ChatClient;
import com.juanite.connection.ClientHandler;
import com.juanite.model.domain.Room;
import com.juanite.model.domain.Message;
import com.juanite.model.domain.User;

import java.net.Socket;
import java.util.Set;

public class AppData {

    private static User currentUser;
    private static Room currentRoom;
    private static ChatClient cc = new ChatClient();

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

    public static ChatClient getCc() {
        return cc;
    }

    public static void setCc(ChatClient cc) {
        AppData.cc = cc;
    }
}
