package com.mir.passpocket;


import com.mir.passpocket.Model.AccountModel;
import com.mir.passpocket.Model.NoteModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Note {

    private static Connection connection = DBConnector.Connect();
    private NoteModel note;

    private Note() {}

    private static Note INSTANCE = null;

    public static Note getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Note();
        }
        return INSTANCE;
    }

    public static void delete() throws SQLException {
        String query = "delete from Notes where Id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, Note.getInstance().getNote().getId());
        ps.executeUpdate();
    }

    public NoteModel getNote() {
        return note;
    }

    public void setNote(NoteModel n) {
        note = n;
    }


    public static void addNote(String title, String note) throws SQLException {
        connection = DBConnector.Connect();
        String query = "insert into Notes Values (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, title);
        ps.setString(2, note);
        ps.setInt(3, User.getInstance().getUserId());
        ps.setString(4, User.getInstance().getUserEmail());
        ps.setByte(5, User.getInstance().getUserAdmin());
        ps.executeUpdate();
    }

    public static ObservableList<NoteModel> AllNote() throws SQLException {
        ObservableList<NoteModel> noteList = FXCollections.observableArrayList();
        String query = "select Id, Title, Description from Notes where UserEmail = ?";
        connection = DBConnector.Connect();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, User.getInstance().getUserEmail());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            noteList.add(new NoteModel(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3)
            ));
        }

        return noteList;
    }


    public static void update(String column, String text) throws SQLException {
        String query = "UPDATE Notes SET " + column + " = ? " + "WHERE Id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, text);
        ps.setInt(2, Note.getInstance().getNote().getId());
        ps.executeUpdate();
    }
}
