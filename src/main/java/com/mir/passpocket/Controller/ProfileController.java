package com.mir.passpocket.Controller;

import com.mir.passpocket.Navigator;
import com.mir.passpocket.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {
    @FXML
    private Button profileBtn, statBtn, backBtn, editBtn, deleteBtn;
    @FXML
    private Pane detailsPane;
    @FXML
    private TextField unmaskedPasswordField, nameField, emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox showPassword;
    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        detailsPane.setVisible(false);

        if(User.getInstance().getUserAdmin() == 1) {
            statusLabel.setText("Admin");
        }
        else {
            statusLabel.setText("User");
            statBtn.setVisible(false);
        }

        unmaskedPasswordField.managedProperty().bind(showPassword.selectedProperty());
        unmaskedPasswordField.visibleProperty().bind(showPassword.selectedProperty());

        passwordField.managedProperty().bind(showPassword.selectedProperty().not());
        passwordField.visibleProperty().bind(showPassword.selectedProperty().not());

        unmaskedPasswordField.textProperty().bindBidirectional(passwordField.textProperty());

        profileBtn.setOnAction(e -> showDetails());

        backBtn.setOnAction(e -> toVault());
    }

    private void toVault() {
        try {
            Navigator.navigateTo((Stage) profileBtn.getScene().getWindow(), "vaultView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showDetails() {
        detailsPane.setVisible(true);
        nameField.setText(User.getInstance().getUserName());
        emailField.setText(User.getInstance().getUserEmail());
        passwordField.setText(User.getInstance().getUserPassword());
    }


}
