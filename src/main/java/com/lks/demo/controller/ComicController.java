/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.controller;

import com.lks.demo.model.Comic;
import com.lks.demo.service.ComicService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Neeraj
 */
@RestController
public class ComicController {
    @Resource(name="ComicService")
    private ComicService comicService;
 
    @RequestMapping(value= "/comic/all", method= RequestMethod.GET)
    public List<Comic> getAllComics() {
        System.out.println(this.getClass().getSimpleName() + " - Get all comics service is invoked.");
        return comicService.getAllComics();
    }
    
   @RequestMapping(value= "/comic/add", method= RequestMethod.POST)
    public Comic addNewComic(@RequestBody Comic comic) {
        System.out.println(this.getClass().getSimpleName() + " - Create new comic method is invoked.");
        return comicService.addNewComic(comic);
    }
    
    @RequestMapping(value= "/comic/update/{id}", method= RequestMethod.PUT)
	public Comic updateComic(@RequestBody Comic upComic, @PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Update comic details by id is invoked.");

		Comic comic =  comicService.getComicById(id);
		if (comic==null)
			throw new Exception("Could not find comic with id- " + id);

		/* IMPORTANT - To prevent the overiding of the existing value of the variables in the database, 
		 * if that variable is not coming in the @RequestBody annotation object. */		
		if(upComic.getHero()== null || upComic.getHero().isEmpty())
			upComic.setHero(comic.getHero());
		if(upComic.getTitle()== null || upComic.getTitle().isEmpty())
			upComic.setTitle(comic.getTitle());
		if(upComic.getYear()== 0)
			upComic.setYear(comic.getYear());
                if(upComic.getAuthorId()== 0)
			upComic.setAuthorId(comic.getAuthorId());

		// Required for the "where" clause in the sql query template.
		upComic.setComicId(id);
		return comicService.updateComic(upComic);
	}
        
        @RequestMapping(value= "/comic/delete/{id}", method= RequestMethod.DELETE)
	public void deleteComicById(@PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Delete comic by id is invoked.");

		Comic comic =  comicService.getComicById(id);
		if(comic==null)
			throw new Exception("Could not find comic with id- " + id);

		comicService.deleteComic(id);
	}

    
    
}
