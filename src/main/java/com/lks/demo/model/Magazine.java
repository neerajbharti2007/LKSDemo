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
public class Magazine {
    
    private int magazineId;
    private String title;
    private String type;
    private int year;
    private int authorId;

    public Magazine(int magazineId, String title, String type, int year, int authorId) {
        this.magazineId = magazineId;
        this.title = title;
        this.type = type;
        this.year = year;
        this.authorId = authorId;
    }

    public Magazine() {
       
    }

    
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getMagazineId() {
        return magazineId;
    }

    public void setMagazineId(int magazineId) {
        this.magazineId = magazineId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
}
