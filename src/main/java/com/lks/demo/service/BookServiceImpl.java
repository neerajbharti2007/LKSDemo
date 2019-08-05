/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lks.demo.service;

import com.lks.demo.dao.BookDao;
import com.lks.demo.model.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Neeraj
 */
@Service("BookService")
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public Book addNewBook(Book book) throws Exception{
        return bookDao.addNewBook(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public void deleteBook(int bookId) {
        bookDao.deleteBook(bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public Book getBookById(int bookId) throws Exception{
        return bookDao.getBookById(bookId);
    }

}
