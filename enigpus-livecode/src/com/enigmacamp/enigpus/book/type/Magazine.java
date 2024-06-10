package com.enigmacamp.enigpus.book.type;

import com.enigmacamp.enigpus.book.Book;

public class Magazine extends Book {
    public String publicationPeriod;
    public int publicationYear;

    public Magazine(String code, String title, String publicationPeriod, int publicationYear) {
        super(code, title);
        this.publicationPeriod = publicationPeriod;
        this.publicationYear = publicationYear;
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

    public String getPublicationPeriod() {
        return publicationPeriod;
    }

    public void setPublicationPeriod(String publicationPeriod) {
        this.publicationPeriod = publicationPeriod;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }
}
