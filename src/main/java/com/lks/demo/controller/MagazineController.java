/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.controller;

import com.lks.demo.model.Comic;
import com.lks.demo.model.Magazine;
import com.lks.demo.service.AuthorService;
import com.lks.demo.service.MagazineService;
import java.util.List;
import java.util.Optional;
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
public class MagazineController {

    @Resource(name="MagazineService")
    private MagazineService magazineService;
    
    @Resource(name="AuthorService")
    private AuthorService authorService;
 
    @RequestMapping(value= "/magazine/all", method= RequestMethod.GET)
    public List<Magazine> getAllMagazines() {
        System.out.println(this.getClass().getSimpleName() + " - Get all magazines service is invoked.");
        return magazineService.getAllMagazines();
    }
    
   @RequestMapping(value= "/magazine/add", method= RequestMethod.POST)
    public Magazine addNewMagazine(@RequestBody Magazine magazine) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - Create new magazine method is invoked.");
        if(!authorService.isAuthorExist(magazine.getAuthorId())){
             throw new Exception("Author doesn't exist."); 
            }
        return magazineService.addNewMagazine(magazine).get();
    }
    
    @RequestMapping(value= "/magazine/update/{id}", method= RequestMethod.PUT)
	public Magazine updateMagazine(@RequestBody Magazine upMagazine, @PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Update magazine details by id is invoked.");

		Optional <Magazine> magazine =  magazineService.getMagazineById(id);
		if (!magazine.isPresent())
			throw new Exception("Could not find magazine with id- " + id);

		/* IMPORTANT - To prevent the overiding of the existing value of the variables in the database, 
		 * if that variable is not coming in the @RequestBody annotation object. */		
		if(upMagazine.getType()== null || upMagazine.getType().isEmpty())
			upMagazine.setType(magazine.get().getType());
		if(upMagazine.getTitle()== null || upMagazine.getTitle().isEmpty())
			upMagazine.setTitle(magazine.get().getTitle());
		if(upMagazine.getYear()== 0)
			upMagazine.setYear(magazine.get().getYear());
                if(upMagazine.getAuthorId()== 0)
			upMagazine.setAuthorId(magazine.get().getAuthorId());

		// Required for the "where" clause in the sql query template.
		upMagazine.setMagazineId(id);
		return magazineService.updateMagazine(upMagazine).get();
	}
        
        @RequestMapping(value= "/magazine/delete/{id}", method= RequestMethod.DELETE)
	public void deleteMagazineById(@PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Delete magazine by id is invoked.");

		Optional <Magazine> magazine =  magazineService.getMagazineById(id);
		if(!magazine.isPresent())
			throw new Exception("Could not find magazine with id- " + id);

		magazineService.deleteMagazine(id);
	}
        
    @RequestMapping(value= "/magazine/{id}", method= RequestMethod.GET)
    public Magazine getMagazineById(@PathVariable int id) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - Get magazine service is invoked.");
		Optional <Magazine>  magazine=magazineService.getMagazineById(id);
                if(!magazine.isPresent())
                        throw new Exception("Could not find magazine with id- " + id);
                return magazine.get();
    }

    
    
}
