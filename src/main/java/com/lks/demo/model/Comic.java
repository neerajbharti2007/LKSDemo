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
public class Comic {
    private int comicId;
    private String title;
    private int year;
    private String hero;
    private int authorId;

    public Comic(int comicId, String title, int year, String hero, int authorId) {
        this.comicId = comicId;
        this.title = title;
        this.year = year;
        this.hero = hero;
        this.authorId = authorId;
    }

    public Comic() {
        
    }

    
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getComicId() {
        return comicId;
    }

    public void setComicId(int comicId) {
        this.comicId = comicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }
    
}
