package com.juanite.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.juanite.App;
import com.juanite.model.domain.Room;
import com.juanite.model.domain.User;
import com.juanite.util.AppData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NavigationController {
    private Set<Room> roomSet = new HashSet<>();
    @FXML
    public VBox vbx_roomList;
    @FXML
    public Button btn_back;

    public void initialize() throws IOException, ClassNotFoundException {
        roomSet = (HashSet<Room>) AppData.getCc().getIn().readObject();
        if(!roomSet.isEmpty()) {
            for (Room room : roomSet) {
                HBox newHbox = new HBox();
                newHbox.setPrefHeight(60);
                newHbox.setSpacing(10);


                Label newLabel = new Label(room.getName());
                newLabel.setId("lbl_" + newLabel.getText());
                newLabel.setStyle("-fx-font-size: 18px;");

                Button newButton = new Button("Join");
                newButton.setId("btn_" + newLabel.getText());
                newButton.setStyle(
                        "-fx-background-color: #009900; " +
                                "-fx-text-fill: white; " +
                                "-fx-font-size: 16px; " +
                                "-fx-pref-width: 80px;"
                );

                newButton.setOnMouseClicked(event -> {
                    try {
                        joinRoom(newLabel);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                newHbox.getChildren().addAll(newLabel, newButton);
                vbx_roomList.getChildren().add(newHbox);

            }
        }
    }

    @FXML
    private void joinRoom(Label label) throws IOException {
        AppData.getCc().getOut().writeObject(false);
        AppData.getCc().getOut().flush();

        Room room = new Room(label.getText());
        AppData.getCc().getOut().writeObject(room);
        AppData.getCc().getOut().flush();

        AppData.getCc().getOut().writeObject(AppData.getCurrentUser());
        AppData.getCc().getOut().flush();

        AppData.setCurrentRoom(room);

        App.setWindowSize(365, 530);
        App.setRoot("chatRoom");
    }
    @FXML
    private void backToMainMenu() throws IOException {
        AppData.getCc().getOut().writeObject(true);
        AppData.getCc().getOut().flush();
        AppData.setCurrentUser(null);
        App.setWindowSize(358, 291);
        App.setRoot("home");
    }
}