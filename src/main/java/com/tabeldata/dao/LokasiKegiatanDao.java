package com.tabeldata.dao;

import com.tabeldata.dto.LokasiKegiatanDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class LokasiKegiatanDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public List<LokasiKegiatanDto> listLokasiKegiatan() {
        //language=OracleSqlPlus
        String sql = "SELECT C_WIL_LOKASI AS kodeLokasi,\n" +
                "       N_WIL_LOKASI AS namaLokasi\n" +
                "FROM TRRBALOKASI\n" +
                "WHERE C_AKTIF = 1 ";
        Map<String, Object> param = new HashedMap<>();

        return this.namedParameterJdbcTemplate.query(sql, param, (resultSet, i) -> {
            LokasiKegiatanDto lokasi = new LokasiKegiatanDto();
            lokasi.setKodeLokasi(resultSet.getString("kodeLokasi"));
            lokasi.setNamaLokasi(resultSet.getString("namaLokasi"));
            return lokasi;
        });
    }
}
