package com.juanite.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.juanite.App;
import com.juanite.model.domain.Room;
import com.juanite.util.AppData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NavigationController {

    @FXML
    public VBox vbx_roomList;
    @FXML
    public HBox hbx_room;
    @FXML
    public Label lbl_roomName;
    @FXML
    public Button btn_enter;
    @FXML
    public Button btn_back;

    public void initialize() throws IOException, ClassNotFoundException {
        Set<Room> roomSet = (HashSet<Room>) AppData.getCc().getIn().readObject();
        boolean firstIteration = true;
        if(!roomSet.isEmpty()) {
            for (Room room : roomSet) {
                if (firstIteration) {
                    hbx_room.setVisible(true);
                    lbl_roomName.setText(room.getName());
                } else {
                    HBox newHbox = new HBox();
                    Label newLabel = new Label(room.getName());
                    Button newButton = new Button("Join");
                    newHbox.getChildren().add(newLabel);
                    newHbox.getChildren().add(newButton);
                    vbx_roomList.getChildren().add(newHbox);
                }
            }
        }else{
            hbx_room.setVisible(false);
        }
    }
    @FXML
    private void backToMainMenu() throws IOException {
        App.setWindowSize(358, 291);
        App.setRoot("home");
    }
}