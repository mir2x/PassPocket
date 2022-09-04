package com.mir.passpocket.Controller;

import com.mir.passpocket.Account;
import com.mir.passpocket.Category;
import com.mir.passpocket.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddAccountController {

    @FXML
    private Button addBtn, backBtn;
    @FXML
    private TextField nameInput, emailInput, urlInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private ComboBox<String> categoryInput;



    @FXML
    public void initialize() throws SQLException {

        categoryInput.getItems().addAll(Category.allCategories());
        backBtn.setOnAction(e -> toVault());
        addBtn.setOnAction(e -> addAccount());

    }
    private void addAccount() {
        String name = nameInput.getText();
        String email = emailInput.getText();
        String url = urlInput.getText();
        String password = passwordInput.getText();
        String category = categoryInput.getValue();

        try {
            Account.addAccount(name, email, url, password, category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        toVault();
    }

    private void toVault(){
        try {
            Navigator.navigateTo((Stage) backBtn.getScene().getWindow(), "vaultView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
