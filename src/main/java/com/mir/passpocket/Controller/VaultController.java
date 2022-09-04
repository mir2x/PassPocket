package com.mir.passpocket.Controller;

import com.mir.passpocket.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;


public class VaultController {

    @FXML
    private Button addButton, editButton, deleteButton;

    @FXML
    private Hyperlink allItems, favorites, trash, accounts, notes, contacts, password_generator;

    @FXML
    private ListView<AccountModel> allAccount;

    @FXML
    private TextField unmaskedPasswordField, nameField, emailField, urlField, categoryField, modifiedField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox showPassword;

    private int selected = 1;

    @FXML
    private MenuButton userButton;

    @FXML
    public void initialize() throws SQLException {
        unmaskedPasswordField.managedProperty().bind(showPassword.selectedProperty());
        unmaskedPasswordField.visibleProperty().bind(showPassword.selectedProperty());

        passwordField.managedProperty().bind(showPassword.selectedProperty().not());
        passwordField.visibleProperty().bind(showPassword.selectedProperty().not());

        unmaskedPasswordField.textProperty().bindBidirectional(passwordField.textProperty());

        allAccount.getItems().addAll(Account.allAccount());
        allAccount.setCellFactory(listView -> new CustomListCell());
        allAccount.getSelectionModel().select(0);
        allAccount.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setData(newValue));

        password_generator.setOnAction(e -> toPasswordGenerator());
        accounts.setOnAction(e -> addAccount());
        notes.setOnAction(e -> showNotes());
        addButton.setOnAction(e -> add());
        userButton.setText(User.getInstance().getUserName());
    }

    private void setData(AccountModel newValue) {
        nameField.setText(newValue.getName());
        emailField.setText(newValue.getEmail());
        passwordField.setText(newValue.getPassword());
        urlField.setText(newValue.getUrl());
        categoryField.setText(newValue.getCategory());
        modifiedField.setText(newValue.getModified());
    }

    private void addAccount() {
        selected = 1;
    }

    private void add(){
        if(selected == 1) {
            try {
                Navigator.navigateTo((Stage) addButton.getScene().getWindow(), "addAccountView");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if(selected == 2) {
            try {
                Navigator.navigateTo((Stage) addButton.getScene().getWindow(), "addNoteView");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showNotes() {
        selected = 2;
    }

    private void toPasswordGenerator() {
        try {
            Navigator.navigateTo((Stage) addButton.getScene().getWindow(), "generatePasswordView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}