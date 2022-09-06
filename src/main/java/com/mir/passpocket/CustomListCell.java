package com.mir.passpocket;

import com.mir.passpocket.Model.AccountModel;
import com.mir.passpocket.Model.NoteModel;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

public class CustomListCell extends ListCell<AccountModel> {

    private VBox content;
    private Label name;
    private Label email;

    public CustomListCell() {
        super();
        name = new Label();
        name.setStyle("-fx-text-fill: #282828");
        email = new Label();
        email.setStyle("-fx-text-fill: #282828");
        content = new VBox(name, email);
        content.setStyle("-fx-end-margin: 4px");
        content.setSpacing(10);
    }

    @Override
    protected void updateItem(AccountModel item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            name.setText(item.getName());
            email.setText(item.getEmail());
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }



}
