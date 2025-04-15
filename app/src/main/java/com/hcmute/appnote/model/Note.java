package com.hcmute.appnote.model;

import java.io.Serializable;

public class Note implements Serializable {
    private Integer noteId;
    private String nameNote;

    public Note(Integer noteId, String nameNote) {
        this.noteId = noteId;
        this.nameNote = nameNote;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public String getNameNote() {
        return nameNote;
    }

    public void setNameNote(String nameNote) {
        this.nameNote = nameNote;
    }
}
