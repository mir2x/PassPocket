package com.mir.passpocket.Controller;

import com.mir.passpocket.Navigator;
import com.mir.passpocket.User;
import com.mir.passpocket.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField emailInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private Label errorLabel;

    @FXML
    public void initialize(){
        loginBtn.setOnAction(e -> onLogin());
        registerBtn.setOnAction(e -> onRegister());
    }

    private void onLogin() {
        errorLabel.setText("");
        String email = emailInput.getText();
        String password = passwordInput.getText();
        if(Validator.isEmpty(email, password)) {
            errorLabel.setText("* Email and Password cannot be empty!");
        }
        else if(Validator.isEmpty(email)) {
            errorLabel.setText("* Email cannot be empty!");
        }
        else if(Validator.isEmpty(password)) {
            errorLabel.setText("* Password cannot be empty!");
        }
        else {
            try {
                if (Validator.doEmailExist(email)) {
                    if(Validator.doPasswordExist(email, password)) {
                        User.getInstance().setUser(email);
                        Navigator.navigateTo((Stage) registerBtn.getScene().getWindow(), "vaultView");
                    }
                    else
                        errorLabel.setText("* Passwords do not match!");
                }
                else {
                    errorLabel.setText("* No Account found with this email!");
                }
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void onRegister() {
        try {
            Navigator.navigateTo((Stage) registerBtn.getScene().getWindow(), "registerView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
