package com.mir.passpocket.Model;

public class NoteModel {
    private int id;
    private String title;
    private String note;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public NoteModel(int id, String title, String note) {
        this.id = id;
        this.title = title;
        this.note = note;
    }
}
