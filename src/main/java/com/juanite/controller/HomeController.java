package com.juanite.controller;

import java.io.IOException;

import com.juanite.App;
import com.juanite.connection.ChatClient;
import com.juanite.util.AppData;
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
    public void initialize() {
        lbl_userError.setVisible(false);
        lbl_roomError.setVisible(false);
    }

    @FXML
    private void enter() throws IOException {
        lbl_userError.setVisible(false);
        lbl_roomError.setVisible(false);
        if(!emptyUserField()) {
            App.setWindowSize(350, 497);
            App.setRoot("navigation");
        }
    }

    @FXML
    private void createRoom() throws IOException {
        lbl_userError.setVisible(false);
        lbl_roomError.setVisible(false);
        emptyUserField();
        emptyRoomField();
        if(!emptyUserField() && !emptyRoomField()) {
            App.setWindowSize(350, 497);
            App.setRoot("navigation");
        }
    }
    @FXML
    private boolean emptyUserField() {
        if(!txtfld_name.getText().equals("")) {
            lbl_userError.setVisible(false);
            lbl_roomError.setVisible(false);
            return false;
        }else{
            lbl_userError.setText("Insert username");
            lbl_userError.setVisible(true);
            return true;
        }
    }

    @FXML
    private boolean emptyRoomField() {
        if(!txtfld_roomName.getText().equals("")) {
            lbl_roomError.setVisible(false);
            return false;
        }else{
            lbl_roomError.setText("Insert room name");
            lbl_roomError.setVisible(true);
            return true;
        }
    }
}
