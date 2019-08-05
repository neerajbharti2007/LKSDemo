/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.dao;

import com.lks.demo.model.Author;
import java.util.List;

/**
 *
 * @author Neeraj
 */
public interface AuthorDao {
    public Author addNewAuthor(Author author);
    public Author updateAuthor(Author author);
    public void deleteAuthor(int authorId);
    public List<Author> getAllAuthors();
    public Author getAuthorById(int authorId);
        
}
