package com.juanite.controller;

import java.io.IOException;

import com.juanite.App;
import com.juanite.connection.ChatClient;
import com.juanite.model.domain.Room;
import com.juanite.model.domain.User;
import com.juanite.util.AppData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
    private AnchorPane anchorPane;

    @FXML
    public void initialize() {
        lbl_userError.setVisible(false);
        lbl_roomError.setVisible(false);
    }

    @FXML
    private void enter() throws IOException, ClassNotFoundException {
        lbl_userError.setVisible(false);
        lbl_roomError.setVisible(false);
        if(!emptyUserField()) {
            User user = new User(txtfld_name.getText());
            AppData.getCc().getOut().writeObject(user);
            AppData.getCc().getOut().flush();

            boolean isAvailable = (boolean) AppData.getCc().getIn().readObject();
            if(isAvailable) {
                AppData.setCurrentUser(user);

                //False = No need to check for room name availability
                AppData.getCc().getOut().writeObject(false);
                AppData.getCc().getOut().flush();

                App.setWindowSize(350, 497);
                App.setRoot("navigation");
            }else{
                lbl_userError.setText("Username already in use");
                lbl_userError.setVisible(true);
            }
        }
    }

    @FXML
    private void createRoom() throws IOException, ClassNotFoundException {
        lbl_userError.setVisible(false);
        lbl_roomError.setVisible(false);
        emptyUserField();
        emptyRoomField();
        if(!emptyUserField() && !emptyRoomField()) {
            User user = new User(txtfld_name.getText());
            AppData.getCc().getOut().writeObject(user);
            AppData.getCc().getOut().flush();

            boolean isAvailable = (boolean) AppData.getCc().getIn().readObject();
            if(isAvailable) {
                AppData.setCurrentUser(user);

                //True = Need to check for room name availability
                AppData.getCc().getOut().writeObject(true);
                AppData.getCc().getOut().flush();

                Room room = new Room(txtfld_roomName.getText());
                AppData.getCc().getOut().writeObject(room);
                AppData.getCc().getOut().flush();
                isAvailable = (boolean) AppData.getCc().getIn().readObject();
                if(isAvailable) {
                    AppData.setCurrentRoom(room);

                    App.setWindowSize(350, 497);
                    App.setRoot("navigation");
                }else{
                    lbl_roomError.setText("Room name already in use");
                    lbl_roomError.setVisible(true);
                }
            }else{
                lbl_userError.setText("Username already in use");
                lbl_userError.setVisible(true);
            }
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
