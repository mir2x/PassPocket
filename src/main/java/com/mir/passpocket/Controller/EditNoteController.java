package com.mir.passpocket.Controller;

import com.mir.passpocket.Account;
import com.mir.passpocket.Navigator;
import com.mir.passpocket.Note;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EditNoteController {
    @FXML
    private Button updateBtn, backBtn;
    @FXML
    private TextArea noteInput;
    @FXML
    private TextField titleInput;

    private boolean titleChange, noteChange;


    @FXML
    public void initialize() {

        setData();

        titleInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue)) {
                titleChange = true;
            }
        });
        noteInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue)) {
                noteChange = true;
            }
        });

        updateBtn.setOnAction(e ->  updateNote());

        backBtn.setOnAction(e -> toVault());

    }

    private void setData() {
        titleInput.setText(Note.getInstance().getNote().getTitle());
        noteInput.setText(Note.getInstance().getNote().getNote());

    }

    private void toVault(){
        try {
            Navigator.navigateTo((Stage) backBtn.getScene().getWindow(), "noteVaultView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateNote() {
        if(titleChange) {
            try {
                Note.update("Title", titleInput.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(noteChange) {
            try {
                Note.update("Description", noteInput.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Navigator.navigateTo((Stage) updateBtn.getScene().getWindow(), "noteVaultView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
