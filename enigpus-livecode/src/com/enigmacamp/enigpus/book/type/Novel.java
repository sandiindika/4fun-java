package com.enigmacamp.enigpus.book.type;

import com.enigmacamp.enigpus.book.Book;

public class Novel extends Book {
    public String publisher;
    public int publicationYear;
    public String author;

    public Novel(String code, String title, String publisher, int publicationYear, String author) {
        super(code, title);
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.author = author;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
