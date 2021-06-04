package com.example.androidhomework3.models.firebasemodels;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class BookFB {
    private String title;
    private String author;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookFB() {
        title = null;
        author = null;
        description = null;
    }

    public BookFB(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }
}
