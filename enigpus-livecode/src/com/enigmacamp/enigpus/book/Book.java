package com.enigmacamp.enigpus.book;

public abstract class Book {
    protected String code;
    protected String title;

    public Book(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public abstract String getCode();
    public abstract String getTitle();

}
