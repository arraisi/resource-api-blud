package com.tabeldata.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class RbaNoMaxDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Method Untuk Mengambil ID Pada Table TRRBANOMAX dengan Value Id Yang Telah Di +1 (Tambah 1)
     */
    public Integer getIdFromNoMax(String namaTable) {
        String baseQuery = "SELECT I_IDMAX + 1 AS valueId FROM TRRBANOMAX WHERE N_TABEL = :namaTable";
        Map<String, Object> param = new HashMap<>();
        param.put("namaTable", namaTable);
        return this.namedParameterJdbcTemplate.queryForObject(baseQuery, param, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("valueId");
            }
        });
    }

    /**
     * Method For Update ID Di Table TRRBANOMAX  Sesuai Nama Tablenya
     */
    public void updateIdNoMax(Integer idBaru, String namaTable) {
        String query = "update TRRBANOMAX SET I_IDMAX = :idBaru WHERE N_TABEL = :namaTable";
        Map<String, Object> param = new HashMap<>();
        param.put("idBaru", idBaru);
        param.put("namaTable", namaTable);
        this.namedParameterJdbcTemplate.update(query, param);
    }

}
