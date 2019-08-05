/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.service;

import com.lks.demo.model.Comic;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Neeraj
 */
public interface ComicService {
     public Comic addNewComic(Comic comic);
    public Comic updateComic(Comic comic);
    public void deleteComic(int comicId);
    public List<Comic> getAllComics();
    public Comic getComicById(int comicId);
   
}
