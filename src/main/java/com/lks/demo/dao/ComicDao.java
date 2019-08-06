/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.dao;

import com.lks.demo.model.Comic;
import com.lks.demo.model.SearchComic;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Neeraj
 */
public interface ComicDao {
    public Optional<Comic> addNewComic(Comic comic);
    public Optional<Comic> updateComic(Comic comic);
    public void deleteComic(int comicId);
    public List<Comic> getAllComics();
    public Optional<Comic> getComicById(int comicId);
    public List<Comic> findAllComics(SearchComic comic);
   
}
