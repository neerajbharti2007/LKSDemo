/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.service;

import com.lks.demo.dao.AuthorDao;
import com.lks.demo.model.Author;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Neeraj
 */
@Service("AuthorService")
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorDao authorDao; 
    
    @Override
    public Author addNewAuthor(Author author) {
      return authorDao.addNewAuthor(author);
    }

    @Override
    public Author updateAuthor(Author author) {
       return authorDao.updateAuthor(author);
    }

    @Override
    public void deleteAuthor(int authorId) {
        authorDao.deleteAuthor(authorId);
    }

    @Override
    public List<Author> getAllAuthors() {
       return authorDao.getAllAuthors();
    }

    @Override
    public Author getAuthorById(int authorId) {
      return authorDao.getAuthorById(authorId);
    }

    @Override
    public boolean isAuthorExist(int authorId) {
         return authorDao.isAuthorExist(authorId);
    }
    
}
