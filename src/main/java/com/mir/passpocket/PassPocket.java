package com.mir.passpocket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PassPocket extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PassPocket.class.getResource("fxml/loginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PassPocket");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}