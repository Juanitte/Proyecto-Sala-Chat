package com.juanite.controller;

import java.io.IOException;

import com.juanite.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HomeController {

    public Label title;
    public Button getIn_btn;
    public Button create_btn;
    public TextField name_txtfld;

    @FXML
    private void switchToNavigation() throws IOException {
        App.setRoot("navigation");
    }
}
