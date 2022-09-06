package com.mir.passpocket.Controller;

import com.mir.passpocket.*;
import com.mir.passpocket.Model.AccountModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class VaultController {

    @FXML
    private AnchorPane detailedInfo;

    @FXML
    private Button addBtn, editBtn, deleteBtn, favoriteBtn, searchBtn;

    @FXML
    private Hyperlink allItems, favoriteItems, accounts, notes, password_generator;

    @FXML
    private ListView<AccountModel> listAccount;

    @FXML
    private TextField unmaskedPasswordField, nameField, emailField, urlField, categoryField, modifiedField, searchField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox showPassword;

    @FXML
    private Label listTitle;
    @FXML
    private MenuButton userButton;

    @FXML
    private MenuItem profile, logOut;

    @FXML
    public void initialize() throws SQLException {

        detailedInfo.setVisible(false);

        unmaskedPasswordField.managedProperty().bind(showPassword.selectedProperty());
        unmaskedPasswordField.visibleProperty().bind(showPassword.selectedProperty());

        passwordField.managedProperty().bind(showPassword.selectedProperty().not());
        passwordField.visibleProperty().bind(showPassword.selectedProperty().not());

        unmaskedPasswordField.textProperty().bindBidirectional(passwordField.textProperty());

//        Search Button
        searchBtn.setOnAction(e -> searchAccount());

//        User Menu
        userButton.setText(User.getInstance().getUserName());

//        Profile Button
        profile.setOnAction(e -> userProfile());

//        Logout Button
        logOut.setOnAction(e -> logOut());

//        All Items
        allItems.setOnAction(e -> allItems());

//        Favorites
        favoriteItems.setOnAction(e -> favoriteItems());

//        Accounts
        accounts.setOnAction(e ->  allItems());

//        Notes
        notes.setOnAction(e -> showNotes());

//        Password Generator
        password_generator.setOnAction(e -> toPasswordGenerator());

//        Account List
        listAccount.setCellFactory(listView -> new CustomListCell());
        listAccount.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setData(newValue));
        listAccount.getItems().addAll(Account.allAccount());

//        Add Button
        addBtn.setOnAction(e -> addAccount());

//        Favorite Button
        favoriteBtn.setOnAction(e -> addToFavorites());

//        Edit Button
        editBtn.setOnAction(e -> editAccount());

//        Delete Button
        deleteBtn.setOnAction(e -> deleteAccount());

    }

    private void searchAccount(){
        listTitle.setText("Results");
        listAccount.getItems().clear();
        try {
            listAccount.getItems().addAll(Account.search(searchField.getText()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void userProfile() {
        try {
            Navigator.navigateTo((Stage) addBtn.getScene().getWindow(), "profileView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void logOut() {
        AccountModel account = Account.getInstance().getAccount();
        account = null;
        try {
            Navigator.navigateTo((Stage) addBtn.getScene().getWindow(), "loginView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void allItems(){
        listTitle.setText("All Items");
        listAccount.getItems().clear();
        try {
            listAccount.getItems().addAll(Account.allAccount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void favoriteItems() {
        listTitle.setText("Favorites");
        listAccount.getItems().clear();
        try {
            listAccount.getItems().addAll(Account.favoriteAccount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showNotes() {
        try {
            Navigator.navigateTo((Stage) addBtn.getScene().getWindow(), "noteVaultView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void toPasswordGenerator() {
        try {
            Navigator.navigateTo((Stage) addBtn.getScene().getWindow(), "generatePasswordView");
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

    private void addAccount(){
        try {
            Navigator.navigateTo((Stage) addBtn.getScene().getWindow(), "addAccountView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addToFavorites(){
        try {
            Account.addToFavorites(Account.getInstance().getAccount().getId(), User.getInstance().getUserEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void editAccount() {
        try {
            Navigator.navigateTo((Stage) addBtn.getScene().getWindow(), "editAccountView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteAccount(){
        Alert alert = new Alert(Alert.AlertType.WARNING, "Do you want to remove this?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES) {
            try {
                Account.deleteAccount();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            listAccount.getItems().clear();
            try {
                listAccount.getItems().addAll(Account.allAccount());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}