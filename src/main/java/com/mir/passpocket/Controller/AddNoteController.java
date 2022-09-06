package com.mir.passpocket.Controller;

import com.mir.passpocket.Navigator;
import com.mir.passpocket.Note;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddNoteController {
    @FXML
    private Button addBtn, backBtn;
    @FXML
    private TextArea noteInput;
    @FXML
    private TextField titleInput;

    @FXML
    public void initialize() {

        addBtn.setOnAction(e -> {
            try {
                addNote();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        backBtn.setOnAction(e -> toVault());
        
    }

    private void addNote() throws SQLException {
        Note.addNote(titleInput.getText(), noteInput.getText());
        try {
            Navigator.navigateTo((Stage) addBtn.getScene().getWindow(), "noteVaultView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void toVault() {
     try {
      Navigator.navigateTo((Stage) backBtn.getScene().getWindow(), "noteVaultView");
     } catch (IOException e) {
      throw new RuntimeException(e);
     }
    }
}
