/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lks.demo.dao;

import com.lks.demo.model.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Neeraj
 */
@Component
@Repository
@Transactional
@Qualifier("BookDao")
public class BookDaoImpl extends JdbcDaoSupport implements BookDao {

    @Autowired
    public BookDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    private static final String INSERT_SQL = "INSERT INTO book  (id,title,genre,year,authorid) VALUES (?,?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE book set title=? ,genre=? , year = ?, authorid=? where id = ?";
    private static final String DELETE_SQL = "DELETE book  where id = ?";
    private static final String SELECT_SQL = "select id,title,genre,year,authorid  from book";
    private static final String SELECT_BY_ID_SQL = "select id, title,genre,year,authorid  from book where id=?";
    private static final String AUTHOR_EXIST_SQL = "SELECT count(*) from author where id=?";
    private static final String BOOK_EXIST_SQL = "SELECT count(*) from book where id=?";

    @Override
    public Book addNewBook(Book book) throws Exception {
        if(!isAuthorExist(book.getAuthorId())){
            throw new Exception("Author doesn't exist" );
        }
        
        int i = this.getJdbcTemplate().update(
                INSERT_SQL,
                new Object[]{book.getBookId(), book.getTitle(), book.getGenre(), book.getYear(), book.getAuthorId()});
        return getBookById(book.getBookId());
    }

    @Override
    public Book updateBook(Book book) {
        int i = this.getJdbcTemplate().update(
                UPDATE_SQL,
                new Object[]{book.getTitle(), book.getGenre(), book.getYear(), book.getAuthorId(), book.getBookId()

                });

        try {
            return getBookById(book.getBookId());
        } catch (Exception ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void deleteBook(int bookId) {
        this.getJdbcTemplate().update(
                DELETE_SQL,
                new Object[]{bookId
                });

    }

    @Override
    public List<Book> getAllBooks() {
// Maps a SQL result to a Java object
        RowMapper<Book> mapper = new RowMapper<Book>() {

            @Override
            public Book mapRow(ResultSet rs, int i) throws SQLException {
                Book book = new Book();
                book.setAuthorId(rs.getInt("authorid"));
                book.setBookId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setGenre(rs.getString("genre"));
                book.setYear(rs.getInt("year"));

                return book;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        return this.getJdbcTemplate().query(SELECT_SQL, mapper);

    }

    @Override
    public Book getBookById(int bookId) throws Exception {

         if(!isBookExist(bookId)){
            throw new Exception("Book doesn't exist" );
        }
        RowMapper<Book> mapper = new RowMapper<Book>() {

            @Override
            public Book mapRow(ResultSet rs, int i) throws SQLException {
                Book book = new Book();
                book.setAuthorId(rs.getInt("authorid"));
                book.setBookId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setGenre(rs.getString("genre"));
                book.setYear(rs.getInt("year"));

                return book;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        return (Book) this.getJdbcTemplate().queryForObject(SELECT_BY_ID_SQL, mapper, new Object[]{bookId});
    }

    private boolean isAuthorExist(int authorId) {
        boolean result = false;
        int count = this.getJdbcTemplate().queryForObject(AUTHOR_EXIST_SQL, new Object[]{authorId}, Integer.class);
        if (count > 0) {
            result = true;
        }
        return result;
    }
    
    private boolean isBookExist(int authorId) {
        boolean result = false;
        int count = this.getJdbcTemplate().queryForObject(AUTHOR_EXIST_SQL, new Object[]{authorId}, Integer.class);
        if (count > 0) {
            result = true;
        }
        return result;
    }
}
