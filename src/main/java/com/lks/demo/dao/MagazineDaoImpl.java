/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lks.demo.dao;

import com.lks.demo.model.Magazine;
import com.lks.demo.model.SearchGenre;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Qualifier("MagazineDao")
public class MagazineDaoImpl extends JdbcDaoSupport implements MagazineDao {

    @Autowired
    public MagazineDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    private static final String INSERT_SQL = "INSERT INTO magazine  (id,title,type,year,authorid) VALUES (?,?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE magazine set title=? ,type=? , year = ?, authorid=? where id = ?";
    private static final String DELETE_SQL = "DELETE magazine  where id = ?";
    private static final String SELECT_SQL = "SELECT id,title,type,year,authorid  from magazine";
    private static final String SELECT_BY_ID_SQL = "SELECT id,title,type,year,authorid  from magazine where id=?";
    private static final String FIND_GENRE_SQL = "SELECT magazine.id,title,type,year,authorid  FROM magazine, author where magazine.authorid=author.id" +
                                                " AND (( lower(author.name)  =? ) OR ('0' = ?)) " +
                                                " AND (( lower(magazine.type)  =? ) OR ('0' = ?)) " +
                                                " AND (( year  =? ) OR ('0' = ?))";
    
    
    @Override
    public Optional<Magazine> addNewMagazine(Magazine magazine) {
        this.getJdbcTemplate().update(
                INSERT_SQL,
                new Object[]{magazine.getMagazineId(), magazine.getTitle(), magazine.getType(), magazine.getYear(), magazine.getAuthorId()});
        return getMagazineById(magazine.getMagazineId());
    }

    @Override
    public Optional<Magazine> updateMagazine(Magazine magazine) {
        this.getJdbcTemplate().update(
                UPDATE_SQL,
                new Object[]{magazine.getTitle(), magazine.getType(), magazine.getYear(), magazine.getAuthorId(), magazine.getMagazineId()

                });

        return getMagazineById(magazine.getMagazineId());
    }

    @Override
    public void deleteMagazine(int magazineId) {
        this.getJdbcTemplate().update(
                DELETE_SQL,
                new Object[]{magazineId
                });
    }

    @Override
    public List<Magazine> getAllMagazines() {// Maps a SQL result to a Java object
       return this.getJdbcTemplate().query(
                SELECT_SQL,
                (rs, rowNum) ->
                        new Magazine(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getString("type"),
                                rs.getInt("year"),
                                rs.getInt("authorid")

                        )
        );
    }

    @Override
    public Optional<Magazine> getMagazineById(int magazineId) {
        return this.getJdbcTemplate().queryForObject(
                SELECT_BY_ID_SQL,
                new Object[]{magazineId},
                (rs, rowNum) ->
                        Optional.of(new Magazine(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getString("type"),
                                rs.getInt("year"),
                                rs.getInt("authorid")
                        ))
        );
    }

    @Override
    public List<Magazine> findAllGenre(SearchGenre genre) {
        return this.getJdbcTemplate().query(
                FIND_GENRE_SQL,
                new Object[]{genre.getAuthorName().toLowerCase(),genre.getAuthorName().toLowerCase(),genre.getType().toLowerCase(),genre.getType().toLowerCase(),genre.getYear(),genre.getYear()},
                (rs, rowNum) ->
                        new Magazine(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getString("type"),
                                rs.getInt("year"),
                                rs.getInt("authorid")
                        )
        );
    }
}
