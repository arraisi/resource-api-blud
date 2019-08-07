package com.tabeldata.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class LoginDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<String> getTahunAnggaranByNrk(String nrk) throws EmptyResultDataAccessException {

        String query = "SELECT TO_NUMBER(C_ANGG_TAHUN) AS TahunAngg FROM TMRBA WHERE I_NRK_PA = :nrk order by TahunAngg ASC";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue("nrk", nrk);

        return jdbcTemplate.query(query, parameterSource, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                log.info("Data : {}", resultSet.getString("TahunAngg"));
                return resultSet.getString("TahunAngg");
            }
        });


    }


}
