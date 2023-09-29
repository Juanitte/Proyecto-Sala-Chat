package com.juanite.controller;

import java.io.IOException;

import com.juanite.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HomeController {

    @FXML
    public Button getIn_btn;
    @FXML
    public Button create_btn;
    @FXML
    public TextField txtfld_name;
    @FXML
    public Label lbl_userError;
    @FXML
    public TextField txtfld_roomName;
    @FXML
    public Label lbl_roomError;

    @FXML
    private void enter() throws IOException {
        App.setWindowSize(350, 497);
        App.setRoot("navigation");
    }

    @FXML
    private void createRoom() throws IOException {
        App.setWindowSize(350, 497);
        App.setRoot("navigation");
    }
}
