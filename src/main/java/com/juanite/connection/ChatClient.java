package com.juanite.connection;

import com.juanite.model.domain.Message;
import com.juanite.util.AppData;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

public class ChatClient {
    private static final String SERVER_ADDRESS = "172.16.16.115";
    private static final int SERVER_PORT = 8080;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ChatClient() {

    }


    private void connectToServer() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Solicitar al cliente un apodo (nickname)


            // Inicialmente, unirse a una sala
            joinRoom();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void joinRoom() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Unirse a una sala");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese el nombre de la sala:");
        dialog.showAndWait().ifPresent(roomName -> {
            //room = roomName;
            try {
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void sendMessage() throws IOException {
        String msg = ""; //messageField.getText();
        if (!msg.isEmpty()) {
            Message message = new Message(msg, LocalDateTime.now(), AppData.getCurrentUser(), AppData.getCurrentRoom());
            out.writeObject(message);
            out.flush();
            //messageField.clear();
        }
    }

    private void disconnectFromServer() throws IOException {
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

    private void appendMessage(String message) {
        //Platform.runLater(() -> chatArea.appendText(message + "\n"));
    }
}
