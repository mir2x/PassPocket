package com.mir.passpocket.Controller;

import com.mir.passpocket.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneratePasswordController {

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        backButton.setOnAction(event -> {
            try {
                backToVault();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void backToVault() throws IOException {
        Navigator.navigateTo((Stage) backButton.getScene().getWindow(), "vaultView");
    }
}
