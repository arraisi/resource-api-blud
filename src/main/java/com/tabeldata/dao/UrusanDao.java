package com.tabeldata.dao;

import com.tabeldata.entity.UrusanEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UrusanDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<UrusanEntity> getListUrusan(String tahunAnggaran) {
        //language=Oracle
        String sql = "SELECT I_ID     AS id,\n" +
                "       I_URUSAN AS urusan,\n" +
                "       C_URUSAN AS kodeUrusan,\n" +
                "       N_URUSAN AS namaUrusan\n" +
                "FROM TRURUSAN\n" +
                "WHERE I_IDFUNGSI = 7\n" +
                "  AND C_AKTIF = '1'\n" +
                "  AND TO_NUMBER(:vTahunAnggaran) BETWEEN TO_NUMBER(C_TAHUN_BERLAKU) AND TO_NUMBER(C_TAHUN_BERAKHIR) ";
        Map<String, Object> params = new HashedMap<>();
        params.put("vTahunAnggaran", tahunAnggaran);

        return this.namedParameterJdbcTemplate.query(sql, params, (rs, i) -> {
            UrusanEntity urusan = new UrusanEntity();
            urusan.setId(rs.getInt("id"));
            urusan.setUrusan(rs.getString("urusan"));
            urusan.setKodeUrusan(rs.getString("kodeUrusan"));
            urusan.setNamaUrusan(rs.getString("namaUrusan"));
            return urusan;
        });
    }
}
