package com.mir.passpocket.Controller;

import com.mir.passpocket.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;


public class VaultController {

    @FXML
    private AnchorPane detailedInfo;

    @FXML
    private Button addButton, editButton, deleteButton, favoritesBtn;

    @FXML
    private Hyperlink allItems, favoriteItems, trash, accounts, notes, contacts, password_generator;

    @FXML
    private ListView<AccountModel> listAccount;

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

        detailedInfo.setVisible(false);

        unmaskedPasswordField.managedProperty().bind(showPassword.selectedProperty());
        unmaskedPasswordField.visibleProperty().bind(showPassword.selectedProperty());

        passwordField.managedProperty().bind(showPassword.selectedProperty().not());
        passwordField.visibleProperty().bind(showPassword.selectedProperty().not());

        unmaskedPasswordField.textProperty().bindBidirectional(passwordField.textProperty());

        listAccount.getItems().addAll(Account.allAccount());
        listAccount.setCellFactory(listView -> new CustomListCell());
        listAccount.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setData(newValue));

        password_generator.setOnAction(e -> toPasswordGenerator());
        accounts.setOnAction(e -> addAccount());
        notes.setOnAction(e -> showNotes());
        addButton.setOnAction(e -> add());

        favoritesBtn.setOnAction(e -> {
            try {
                addToFavorites();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        editButton.setOnAction(e -> editAccount());
        userButton.setText(User.getInstance().getUserName());

        allItems.setOnAction(e -> {
            try {
                allItems();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        favoriteItems.setOnAction(e -> {
            try {
                favoriteItems();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void allItems() throws SQLException {
        listAccount.getItems().clear();
        listAccount.getItems().addAll(Account.allAccount());
    }

    private void favoriteItems() throws SQLException {
        listAccount.getItems().clear();
        listAccount.getItems().addAll(Account.favoriteAccount());
    }

    private void addToFavorites() throws SQLException {
        Account.addToFavorites(Account.getInstance().getAccount().getId(), User.getInstance().getUserEmail());
    }

    private void editAccount() {
        try {
            Navigator.navigateTo((Stage) addButton.getScene().getWindow(), "editAccountView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setData(AccountModel newValue) {
        detailedInfo.setVisible(true);
        Account.getInstance().setAccount(newValue);
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