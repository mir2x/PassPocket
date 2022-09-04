package com.mir.passpocket;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CustomListCell extends ListCell<AccountModel> {

    private VBox content;
    private Label name;
    private Label email;

    public CustomListCell() {
        super();
        name = new Label();
        email = new Label();
        content = new VBox(name, email);
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
