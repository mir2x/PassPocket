module com.mir.passpocket {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.mir.passpocket to javafx.fxml;
    exports com.mir.passpocket;
    exports com.mir.passpocket.Controller;
    opens com.mir.passpocket.Controller to javafx.fxml;
    exports com.mir.passpocket.Model;
    opens com.mir.passpocket.Model to javafx.fxml;
}