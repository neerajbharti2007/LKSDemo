/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.service;

import com.lks.demo.model.Book;
import com.lks.demo.model.SearchBook;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Neeraj
 */
public interface BookService {
    public Optional<Book> addNewBook(Book book) throws Exception;
    public Optional<Book> updateBook(Book book);
    public void deleteBook(int bookId);
    public List<Book> getAllBooks();
    public Optional<Book> getBookById(int bookId) throws Exception;
    public List<Book> findAllBooks(SearchBook book);
    
   
}
