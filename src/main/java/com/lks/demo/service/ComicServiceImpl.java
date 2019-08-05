/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.service;

import com.lks.demo.dao.ComicDao;
import com.lks.demo.model.Comic;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Neeraj
 */
@Service("ComicService")
public class ComicServiceImpl implements ComicService{

    @Autowired
    ComicDao comicDao; 
    
    @Override
    public Comic addNewComic(Comic comic) {
        return comicDao.addNewComic(comic);
    }

    @Override
    public Comic updateComic(Comic comic) {
        return comicDao.updateComic(comic);
    }

    @Override
    public void deleteComic(int comicId) {
        comicDao.deleteComic(comicId);
    }

    @Override
    public List<Comic> getAllComics() {
        return comicDao.getAllComics();
    }

    @Override
    public Comic getComicById(int comicId) {
        return comicDao.getComicById(comicId);
    }
    
}
