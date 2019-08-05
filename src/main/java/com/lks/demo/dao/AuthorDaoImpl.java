/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.dao;

import com.lks.demo.model.Author;
import com.lks.demo.service.AuthorService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
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
@Qualifier("AuthorDao")
public class AuthorDaoImpl extends JdbcDaoSupport implements AuthorDao{

    private static final String INSERT_SQL = "INSERT INTO author  (name,id) VALUES (?,?)";
    private static final String UPDATE_SQL = "UPDATE author set name = ?,  where id = ?";
    private static final String DELETE_SQL = "DELETE author  where id = ?";
    private static final String SELECT_SQL = "select id, name  from author";
    private static final String SELECT_BY_ID_SQL = "select id, name  from author where id=?";
        
    @Autowired
    public AuthorDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
    @Override
    public Author addNewAuthor(Author author) {
        int i= this.getJdbcTemplate().update(
                INSERT_SQL,
                new Object[]{author.getAuthorName(),author.getAuthorId()});
        
        
      return getAuthorById(author.getAuthorId());
      
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Author updateAuthor(Author author) {
           int i=  this.getJdbcTemplate().update(
            UPDATE_SQL,
                new Object[]{author.getAuthorName(), author.getAuthorId(),
                    });
           
         return getAuthorById(author.getAuthorId());
    }

    @Override
    public void deleteAuthor(int authorId) {
             this.getJdbcTemplate().update(
            DELETE_SQL,
                new Object[]{authorId
                    });
    }

    @Override
    public List<Author> getAllAuthors() {
// Maps a SQL result to a Java object
        RowMapper<Author> mapper = new RowMapper<Author>() {

            @Override
            public Author mapRow(ResultSet rs, int i) throws SQLException {
                Author author = new Author();
                author.setAuthorId(rs.getInt("id"));
                author.setAuthorName(rs.getString("name"));
                
                return author;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        return this.getJdbcTemplate().query(SELECT_SQL, mapper);

        
    }

    @Override
    public Author getAuthorById(int authorId) {
        RowMapper<Author> mapper = new RowMapper<Author>() {

            @Override
            public Author mapRow(ResultSet rs, int i) throws SQLException {
                Author author = new Author();
                author.setAuthorId(rs.getInt("id"));
                author.setAuthorName(rs.getString("name"));
                
                return author;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

return (Author)this.getJdbcTemplate().queryForObject(SELECT_BY_ID_SQL,mapper, new Object[] { authorId });
    }
    
    
}
