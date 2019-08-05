/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.service;

import com.lks.demo.model.Book;
import java.util.List;

/**
 *
 * @author Neeraj
 */
public interface BookService {
    public Book addNewBook(Book book) throws Exception;
    public Book updateBook(Book book);
    public void deleteBook(int bookId);
    public List<Book> getAllBooks();
    public Book getBookById(int bookId) throws Exception;
   
}
