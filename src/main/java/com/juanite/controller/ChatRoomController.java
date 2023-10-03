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

public class ChatRoomController {

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
    }
    @FXML
    private void backToRoomsMenu() throws IOException {
        Message nullMsg = null;
        AppData.getCc().getOut().writeObject(nullMsg);

        AppData.setCurrentRoom(null);
        App.setWindowSize(358, 291);
        App.setRoot("navigation");
    }

    public void sendMessage() {
    }
}