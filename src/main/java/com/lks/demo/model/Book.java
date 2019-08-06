/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.model;

/**
 *
 * @author Neeraj
 */
public class Book {
    
    private int bookId;
    private String title;
    private String genre;
    private int year;
    private int authorId;

    public Book(int bookId, String title, String genre, int year, int authorId) {
        this.bookId = bookId;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authorId = authorId;
    }

    public Book() {
       
    }

    

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
}
