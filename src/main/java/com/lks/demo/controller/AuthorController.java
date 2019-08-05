/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.controller;

import com.lks.demo.model.Author;
import com.lks.demo.service.AuthorService;
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
public class AuthorController {
    
    @Resource(name="AuthorService")
    private AuthorService authorService;
 
    @RequestMapping(value= "/author/all", method= RequestMethod.GET)
    public List<Author> getAllAuthors() {
        System.out.println(this.getClass().getSimpleName() + " - Get all authors service is invoked.");
        return authorService.getAllAuthors();
    }
    
   @RequestMapping(value= "/author/add", method= RequestMethod.POST)
    public Author addNewAuthor(@RequestBody Author author) {
        System.out.println(this.getClass().getSimpleName() + " - Create new author method is invoked.");
        return authorService.addNewAuthor(author);
    }
    
    @RequestMapping(value= "/author/update/{id}", method= RequestMethod.PUT)
	public Author updateAuthor(@RequestBody Author upAuthor, @PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Update author details by id is invoked.");

		Author author =  authorService.getAuthorById(id);
		if (author==null)
			throw new Exception("Could not find author with id- " + id);

		/* IMPORTANT - To prevent the overiding of the existing value of the variables in the database, 
		 * if that variable is not coming in the @RequestBody annotation object. */		
		if(upAuthor.getAuthorName()== null || upAuthor.getAuthorName().isEmpty())
			upAuthor.setAuthorName(author.getAuthorName());
		
		// Required for the "where" clause in the sql query template.
		upAuthor.setAuthorId(id);
		return authorService.updateAuthor(upAuthor);
	}
        
        @RequestMapping(value= "/author/delete/{id}", method= RequestMethod.DELETE)
	public void deleteAuthorById(@PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Delete author by id is invoked.");

		Author auth =  authorService.getAuthorById(id);
		if(auth ==null)
			throw new Exception("Could not find author with id- " + id);

		authorService.deleteAuthor(id);
	}

    
}
