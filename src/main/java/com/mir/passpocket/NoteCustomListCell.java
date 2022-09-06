package com.mir.passpocket;

import com.mir.passpocket.Model.AccountModel;
import com.mir.passpocket.Model.NoteModel;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class NoteCustomListCell extends ListCell<NoteModel> {
    private VBox content;
    private Label title;
    private TextArea note;

    public NoteCustomListCell()  {
        super();
        title = new Label();
        note = new TextArea();
        note.setEditable(false);
//        note.setStyle("-fx-max-height: 20");
        content = new VBox(title, note);
        content.setSpacing(10);
    }

    @Override
    protected void updateItem(NoteModel item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            title.setText(item.getTitle());
            note.setText(item.getNote());
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}
