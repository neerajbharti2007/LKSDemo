/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lks.demo.dao;

import com.lks.demo.model.Comic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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

    @Override
    public Comic addNewComic(Comic comic) {
        int i = this.getJdbcTemplate().update(
                INSERT_SQL,
                new Object[]{comic.getComicId(), comic.getTitle(), comic.getHero(), comic.getYear(), comic.getAuthorId()});
        return getComicById(comic.getComicId());
    }

    @Override
    public Comic updateComic(Comic comic) {
        int i = this.getJdbcTemplate().update(
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
        RowMapper<Comic> mapper = new RowMapper<Comic>() {

            @Override
            public Comic mapRow(ResultSet rs, int i) throws SQLException {
                Comic comic = new Comic();
                comic.setAuthorId(rs.getInt("authorid"));
                comic.setComicId(rs.getInt("id"));
                comic.setTitle(rs.getString("title"));
                comic.setHero(rs.getString("hero"));
                comic.setYear(rs.getInt("year"));

                return comic;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        return this.getJdbcTemplate().query(SELECT_SQL, mapper);

    }

    @Override
    public Comic getComicById(int comicId) {
        RowMapper<Comic> mapper = new RowMapper<Comic>() {

            @Override
            public Comic mapRow(ResultSet rs, int i) throws SQLException {
                Comic comic = new Comic();
                comic.setAuthorId(rs.getInt("authorid"));
                comic.setComicId(rs.getInt("id"));
                comic.setTitle(rs.getString("title"));
                comic.setHero(rs.getString("hero"));
                comic.setYear(rs.getInt("year"));

                return comic;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        return (Comic) this.getJdbcTemplate().queryForObject(SELECT_BY_ID_SQL, mapper, new Object[]{comicId});
    }

}
