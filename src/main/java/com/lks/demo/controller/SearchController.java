/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.controller;

import com.lks.demo.model.Book;
import com.lks.demo.model.Comic;
import com.lks.demo.model.Magazine;
import com.lks.demo.model.SearchBook;
import com.lks.demo.model.SearchComic;
import com.lks.demo.model.SearchGenre;
import com.lks.demo.service.BookService;
import com.lks.demo.service.ComicService;
import com.lks.demo.service.MagazineService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Neeraj
 */
@RestController
public class SearchController {
    @Resource(name="BookService")
    private BookService bookService;
    
    @Resource(name="MagazineService")
    private MagazineService magazineService;
    
    @Resource(name="ComicService")
    private ComicService comicService;
    
    @RequestMapping(value= "/search/findAllBooks", method= RequestMethod.POST)
    public List<Book> findAllBooks(@RequestBody SearchBook search) {
        System.out.println(this.getClass().getSimpleName() + " - Get all search books service is invoked.");
       if (StringUtils.isEmpty(search.getAuthorName())) 
           search.setAuthorName("0");
       if (StringUtils.isEmpty(search.getYear())) 
           search.setYear(0);
       
        return bookService.findAllBooks(search);
    }
    
    @RequestMapping(value= "/search/findAllComics", method= RequestMethod.POST)
    public List<Comic> findAllComics(@RequestBody SearchComic search) {
        System.out.println(this.getClass().getSimpleName() + " - Get all search comics service is invoked.");
       if (StringUtils.isEmpty(search.getHero())) 
           search.setHero("0");
       
       
        return comicService.findAllComics(search);
    }

    @RequestMapping(value= "/search/findAllGenre", method= RequestMethod.POST)
    public List<Magazine> findAllGenre(@RequestBody SearchGenre search) {
        System.out.println(this.getClass().getSimpleName() + " - Get all search Genre service is invoked.");
       if (StringUtils.isEmpty(search.getAuthorName())) 
           search.setAuthorName("0");
       if (StringUtils.isEmpty(search.getYear())) 
           search.setYear(0);
       if (StringUtils.isEmpty(search.getType())) 
           search.setType("0");
       
        return magazineService.findAllGenre(search);
    }
}
