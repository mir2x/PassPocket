package com.mir.passpocket.Controller;

import com.mir.passpocket.CustomListCell;
import com.mir.passpocket.Model.NoteModel;
import com.mir.passpocket.Navigator;
import com.mir.passpocket.Note;
import com.mir.passpocket.NoteCustomListCell;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class NoteVaultController {

    @FXML
    private Button addBtn, editBtn, deleteBtn, backBtn;
    @FXML
    private ListView<NoteModel> listNote;

    @FXML
    public void initialize() throws SQLException {
        listNote.getItems().addAll(Note.AllNote());
        listNote.setCellFactory(listView -> new NoteCustomListCell());
        listNote.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setData(newValue));
        addBtn.setOnAction(e -> addNote());
        editBtn.setOnAction(e -> editNote());
        deleteBtn.setOnAction(e -> deleteNote());
        backBtn.setOnAction(e -> toVault());
    }

    private void deleteNote() {
        try {
            Note.delete();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listNote.getItems().clear();
        try {
            listNote.getItems().addAll(Note.AllNote());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void editNote() {
        try {
            Navigator.navigateTo((Stage) addBtn.getScene().getWindow(), "editNoteView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void toVault() {
        try {
            Navigator.navigateTo((Stage) addBtn.getScene().getWindow(), "vaultView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addNote() {
        try {
            Navigator.navigateTo((Stage) addBtn.getScene().getWindow(), "addNoteView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setData(NoteModel newValue) {
        Note.getInstance().setNote(newValue);
    }

}
