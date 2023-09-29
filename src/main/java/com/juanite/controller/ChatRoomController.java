package com.juanite.controller;

import com.juanite.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ChatRoomController {

    @FXML
    public VBox vbx_roomList;
    @FXML
    public HBox hbx_room;
    @FXML
    public Label lbl_roomName;
    @FXML
    public Button btn_enter;

    @FXML
    private void backToMainMenu() throws IOException {
        App.setWindowSize(358, 291);
        App.setRoot("home");
    }
}