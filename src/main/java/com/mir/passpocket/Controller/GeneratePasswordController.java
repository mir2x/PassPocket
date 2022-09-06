package com.mir.passpocket.Controller;

import com.mir.passpocket.Navigator;
import com.mir.passpocket.PasswordGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneratePasswordController {

    @FXML
    private Button generateBtn, cancelBtn;

    @FXML
    private TextField passwordArea;

    @FXML
    private Spinner<Integer> length;

    @FXML
    private CheckBox upperCheck, lowerCheck, digitCheck, specialCheck;

    @FXML
    public void initialize() {

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(6, 20, 8);
        length.setValueFactory(valueFactory);

        generateBtn.setOnAction(e -> generatePassword());

        cancelBtn.setOnAction(event -> backToVault());
    }

    private void generatePassword() {

        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(digitCheck.isSelected())
                .useLower(lowerCheck.isSelected())
                .useUpper(upperCheck.isSelected())
                .usePunctuation(specialCheck.isSelected())
                .build();
        String password = passwordGenerator.generate(length.getValue());
        passwordArea.setText(password);

    }

    private void backToVault() {
        try {
            Navigator.navigateTo((Stage) cancelBtn.getScene().getWindow(), "vaultView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
