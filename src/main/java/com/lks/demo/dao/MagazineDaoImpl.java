/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lks.demo.dao;

import com.lks.demo.model.Magazine;
import com.lks.demo.model.Magazine;
import com.lks.demo.model.Magazine;
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
@Qualifier("MagazineDao")
public class MagazineDaoImpl extends  JdbcDaoSupport implements MagazineDao{
    
    @Autowired
    public MagazineDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    private static final String INSERT_SQL = "INSERT INTO magazine  (id,title,type,year,authorid) VALUES (?,?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE magazine set title=? ,type=? , year = ?, authorid=? where id = ?";
    private static final String DELETE_SQL = "DELETE magazine  where id = ?";
    private static final String SELECT_SQL = "select id,title,type,year,authorid  from magazine";
    private static final String SELECT_BY_ID_SQL = "select id,title,type,year,authorid  from magazine where id=?";

    @Override
    public Magazine addNewMagazine(Magazine magazine) {
        int i = this.getJdbcTemplate().update(
                INSERT_SQL,
                new Object[]{magazine.getMagazineId(), magazine.getTitle(), magazine.getType(), magazine.getYear(), magazine.getAuthorId()});
        return getMagazineById(magazine.getMagazineId());
    }


    @Override
    public Magazine updateMagazine(Magazine magazine) {
        int i = this.getJdbcTemplate().update(
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
        RowMapper<Magazine> mapper = new RowMapper<Magazine>() {

            @Override
            public Magazine mapRow(ResultSet rs, int i) throws SQLException {
                Magazine magazine = new Magazine();
                magazine.setAuthorId(rs.getInt("authorid"));
                magazine.setMagazineId(rs.getInt("id"));
                magazine.setTitle(rs.getString("title"));
                magazine.setType(rs.getString("type"));
                magazine.setYear(rs.getInt("year"));

                return magazine;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        return this.getJdbcTemplate().query(SELECT_SQL, mapper);

    }


    @Override
    public Magazine getMagazineById(int magazineId) {
        RowMapper<Magazine> mapper = new RowMapper<Magazine>() {

            @Override
            public Magazine mapRow(ResultSet rs, int i) throws SQLException {
                Magazine magazine = new Magazine();
                magazine.setAuthorId(rs.getInt("authorid"));
                magazine.setMagazineId(rs.getInt("id"));
                magazine.setTitle(rs.getString("title"));
                magazine.setType(rs.getString("type"));
                magazine.setYear(rs.getInt("year"));

                return magazine;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        return (Magazine) this.getJdbcTemplate().queryForObject(SELECT_BY_ID_SQL, mapper, new Object[]{magazineId});
    }

}
