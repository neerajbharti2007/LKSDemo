/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lks.demo.dao;

import com.lks.demo.model.Comic;
import com.lks.demo.model.SearchComic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
@Qualifier("ComicDao")
public class ComicDaoImpl extends JdbcDaoSupport implements ComicDao {

    @Autowired
    public ComicDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    private static final String INSERT_SQL = "INSERT INTO comic  (id,title,hero,year,authorid) VALUES (?,?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE comic set title=? ,hero=? , year = ?, authorid=? where id = ?";
    private static final String DELETE_SQL = "DELETE comic  where id = ?";
    private static final String SELECT_SQL = "select id,title,hero,year,authorid  from comic";
    private static final String SELECT_BY_ID_SQL = "select id,title,hero,year,authorid  from comic where id=?";
    private static final String FIND_COMIC_SQL = "SELECT comic.id,title,hero,year,authorid  from comic, author where comic.authorid=author.id" +
                                                " AND (( lower(hero)  =? ) OR ('0' = ?))" ;
                                                
 
    
    @Override
    public Optional<Comic> addNewComic(Comic comic) {
        this.getJdbcTemplate().update(
                INSERT_SQL,
                new Object[]{comic.getComicId(), comic.getTitle(), comic.getHero(), comic.getYear(), comic.getAuthorId()});
        return getComicById(comic.getComicId());
    }

    @Override
    public Optional<Comic> updateComic(Comic comic) {
        this.getJdbcTemplate().update(
                UPDATE_SQL,
                new Object[]{comic.getTitle(), comic.getHero(), comic.getYear(), comic.getAuthorId(), comic.getComicId()

                });

        return getComicById(comic.getComicId());
    }

    @Override
    public void deleteComic(int comicId) {
        this.getJdbcTemplate().update(
                DELETE_SQL,
                new Object[]{comicId
                });
    }

    @Override
    public List<Comic> getAllComics() {// Maps a SQL result to a Java object
       return this.getJdbcTemplate().query(
                SELECT_SQL,
                (rs, rowNum) ->
                        new Comic(
                           rs.getInt("id"),
                                rs.getString("title"),
                                rs.getInt("year"),
                                rs.getString("hero"),
                                rs.getInt("authorid")
                        )
        );
    }

    @Override
    public Optional<Comic> getComicById(int comicId) {
    return this.getJdbcTemplate().queryForObject(
                SELECT_BY_ID_SQL,
                new Object[]{comicId},
                (rs, rowNum) ->
                        Optional.of(new Comic(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getInt("year"),
                                rs.getString("hero"),
                                rs.getInt("authorid")
                        ))
        );
    }

    @Override
    public List<Comic> findAllComics(SearchComic comic) {
        return this.getJdbcTemplate().query(
                FIND_COMIC_SQL,
                new Object[]{comic.getHero().toLowerCase(),comic.getHero().toLowerCase()},
                (rs, rowNum) ->
                        new Comic(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getInt("year"),
                                rs.getString("hero"),
                                rs.getInt("authorid")
                        )
        );
 
    }
}