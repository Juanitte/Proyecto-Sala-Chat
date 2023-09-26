package com.juanite.controller;

import java.io.IOException;

import com.juanite.App;
import javafx.fxml.FXML;

public class NavigationController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}