/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lks.demo.dao;

import com.lks.demo.model.Book;
import com.lks.demo.model.SearchBook;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
    private static final String FIND_BOOK_SQL = "SELECT book.id,title,genre,year,authorid  FROM book, author where book.authorid=author.id " +
                                                " AND (( lower(author.name)  =? ) OR ('0' = ?))" +
                                                " AND (( lower(book.year)  =? ) OR ('0' = ?))";
    

    @Override
    public Optional<Book> addNewBook(Book book) throws Exception {
        this.getJdbcTemplate().update(
                INSERT_SQL,
                new Object[]{book.getBookId(), book.getTitle(), book.getGenre(), book.getYear(), book.getAuthorId()});
        return getBookById(book.getBookId());
    }

    @Override
    public Optional<Book> updateBook(Book book) {
        this.getJdbcTemplate().update(
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
        return this.getJdbcTemplate().query(
                SELECT_SQL,
                (rs, rowNum) ->
                        new Book(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getString("genre"),
                                rs.getInt("year"),
                                rs.getInt("authorid")
                        )
        );

    }

    @Override
    public Optional<Book> getBookById(int bookId) throws Exception {

        return this.getJdbcTemplate().queryForObject(
                SELECT_BY_ID_SQL,
                new Object[]{bookId},
                (rs, rowNum) ->
                        Optional.of(new Book(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getString("genre"),
                                rs.getInt("year"),
                                rs.getInt("authorid")
                        ))
        );
    }

    @Override
    public List<Book> findAllBooks(SearchBook book) {
        return this.getJdbcTemplate().query(
                FIND_BOOK_SQL,
                new Object[]{book.getAuthorName().toLowerCase(),book.getAuthorName().toLowerCase(),book.getYear(),book.getYear()},
                (rs, rowNum) ->
                        new Book(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getString("genre"),
                                rs.getInt("year"),
                                rs.getInt("authorid")
                        )
        );
        
    }

}
