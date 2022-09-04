package com.mir.passpocket.Controller;

import com.mir.passpocket.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AddNoteController {
    @FXML
    private Button backBtn;

    @FXML
    public void initialize() {
        backBtn.setOnAction(e -> toVault());
        
    }

    private void toVault() {
     try {
      Navigator.navigateTo((Stage) backBtn.getScene().getWindow(), "vaultView");
     } catch (IOException e) {
      throw new RuntimeException(e);
     }
    }
}
