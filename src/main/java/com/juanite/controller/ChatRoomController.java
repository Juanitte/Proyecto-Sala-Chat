package com.juanite.controller;

import com.juanite.App;
import com.juanite.model.domain.Message;
import com.juanite.util.AppData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

public class ChatRoomController {

    private volatile boolean stopMessageListening = false;
    private Thread messageListenerThread;
    @FXML
    public Button btn_back;
    @FXML
    public TextArea txtarea_chatPanel;
    @FXML
    public TextField txtfld_msg;
    @FXML
    public Button btn_send;
    @FXML
    public Label lbl_roomName;

    public void initialize() {
        lbl_roomName.setText(AppData.getCurrentRoom().getName());
        if(messageListenerThread == null) {
            messageListenerThread = new Thread(this::listenForMessages);
            messageListenerThread.setDaemon(true);
        }
            messageListenerThread.start();
    }

    private void listenForMessages() {
        while(!stopMessageListening) {
            try {
                Message receivedMessage = (Message) AppData.getCc().getIn().readObject();
                if(receivedMessage != null) {
                    String message = "[" + receivedMessage.getTimestamp() + "] " + receivedMessage.getUser().getUsername() + ": " + receivedMessage.getMessage();
                    txtarea_chatPanel.appendText(message + "\n");
                }

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    private void backToRoomsMenu() throws IOException {
        stopMessageListening = true;


        Message nullMsg = null;
        AppData.getCc().getOut().writeObject(nullMsg);
        AppData.getCc().getOut().flush();

        try {
            // Espera a que el hilo de escucha se complete
            messageListenerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AppData.setCurrentRoom(null);
        App.setWindowSize(350, 497);
        App.setRoot("navigation");
    }

    public void sendMessage() throws IOException {
        if(!txtfld_msg.getText().equals("")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
            Message message = new Message(txtfld_msg.getText(), LocalDateTime.now().format(dtf), AppData.getCurrentUser(), AppData.getCurrentRoom());
            AppData.getCc().getOut().writeObject(message);
            AppData.getCc().getOut().flush();
            txtfld_msg.setText("");
        }
    }
}