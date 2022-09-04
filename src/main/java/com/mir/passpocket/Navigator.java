package com.mir.passpocket;

import com.mir.passpocket.Controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {
    public static void navigateTo(Stage stage, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PassPocket.class.getResource("fxml/" + fxml + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
