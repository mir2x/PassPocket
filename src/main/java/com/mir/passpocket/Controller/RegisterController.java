package com.mir.passpocket.Controller;

import com.mir.passpocket.Navigator;
import com.mir.passpocket.User;
import com.mir.passpocket.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private Button registerBtn, loginBtn;
    @FXML
    private TextField nameInput, emailInput;
    @FXML
    private PasswordField passInput, repeatPassInput;
    @FXML
    private Label errorLabel;
    @FXML
    private CheckBox termsAndCondition;


    @FXML
    public void initialize(){

        registerBtn.setOnAction(e -> {
            try {
                register();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        loginBtn.setOnAction(e -> toLogin());
    }

    private void register() throws SQLException {
        errorLabel.setText("");
        String name = nameInput.getText();
        String email = emailInput.getText();
        String password = passInput.getText();
        String repeat_password = repeatPassInput.getText();

        if(Validator.isEmpty(name, email, password, repeat_password)) {
            errorLabel.setText("* All field is empty. Fill them up!");
        }
        else if(Validator.isEmpty(name)) {
            errorLabel.setText("* Username cannot be empty!");
        }
        else if(Validator.isEmpty(email)) {
            errorLabel.setText("* Email cannot be empty!");
        }
        else if(Validator.isEmpty(password)) {
            errorLabel.setText("* Password cannot be empty!");
        }
        else if(Validator.isEmpty(repeat_password)) {
            errorLabel.setText("* Please! Retype the password.");
        }
        else if (Validator.doNameExist(name)) {
            errorLabel.setText("* Username is already taken!");
        }
        else if (Validator.isNameTooLong(name)) {
            errorLabel.setText("* Username is very long!");
        }
        else if (Validator.isNameTooSmall(name)) {
            errorLabel.setText("* Username is very small!");
        }
        else if(!Validator.isEmailValid(email)) {
            errorLabel.setText("* Email is not valid!");
        }
        else if (Validator.doEmailExist(email)) {
            errorLabel.setText("* Email is already taken!");
        }
        else if(!Validator.isPasswordValid(password)) {
            errorLabel.setText("* Password is weak");
        }
        else if(!Validator.doPasswordMatch(password, repeat_password)){
            errorLabel.setText("* Passwords do not match");
        }
        else if(!termsAndCondition.isSelected()) {
            errorLabel.setText("* You must accept terms and condition!");
        }
        else {
           User.addUser(name, email, password, 0);
           toLogin();
        }
        
    }

    private void toLogin() {
        try {
            Navigator.navigateTo((Stage) loginBtn.getScene().getWindow(), "loginView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
