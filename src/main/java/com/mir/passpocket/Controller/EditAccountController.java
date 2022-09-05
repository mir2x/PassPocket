package com.mir.passpocket.Controller;

import com.mir.passpocket.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class EditAccountController {
    @FXML
    private TextField nameInput, emailInput, urlInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private ComboBox<String> categoryInput;
    @FXML
    private Button updateBtn, cancelBtn;
    private boolean nameChange, emailChange, urlChange, passwordChange, categoryChange = false;

    private Connection connection;

    @FXML
    public void initialize() throws SQLException {

        categoryInput.getItems().addAll(Category.allCategories());

        setData();

        nameInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue)) {
               nameChange = true;
            }
        });
        emailInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue)) {
                emailChange = true;
            }
        });
        urlInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue)) {
                urlChange = true;
            }
        });
        passwordInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue)) {
                passwordChange = true;
            }
        });
        categoryInput.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if(!oldValue.equals(newValue)) {
                categoryChange = true;
            }
        });


        updateBtn.setOnAction(e -> {
            try {
                updateAccount();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });


        cancelBtn.setOnAction(e -> toVault());

    }

    private void setData() {
        nameInput.setText(Account.getInstance().getAccount().getName());
        emailInput.setText(Account.getInstance().getAccount().getEmail());
        urlInput.setText(Account.getInstance().getAccount().getUrl());
        passwordInput.setText(Account.getInstance().getAccount().getPassword());
        categoryInput.getSelectionModel().select(Account.getInstance().getAccount().getCategory());
    }

    private void toVault() {
        try {
            Navigator.navigateTo((Stage) nameInput.getScene().getWindow(), "vaultView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateAccount() throws SQLException {
        if(nameChange) {
            Account.update("Name", nameInput);
        }

        if(emailChange) {
            Account.update("Email", emailInput);
        }

        if(urlChange) {
            Account.update("Url", urlInput);
        }

        if(passwordChange) {
           Account.update("Password", passwordInput);
        }

        if(categoryChange) {
            Account.update("Category", categoryInput);
        }

    }



}
