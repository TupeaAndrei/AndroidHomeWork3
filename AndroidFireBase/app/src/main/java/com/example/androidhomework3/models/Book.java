package com.example.androidhomework3.models;

import com.example.androidhomework3.models.firebasemodels.BookFB;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private String description;

    public Book(){
        title = null;
        author = null;
        description = null;
    }

    public Book(BookFB fb)
    {
        this.title = fb.getTitle();
        this.author = fb.getAuthor();
        this.description = fb.getDescription();
    }

    public Book(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, description);
    }
}
