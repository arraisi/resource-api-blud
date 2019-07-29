package com.tabeldata.dao;

import com.tabeldata.entity.LoadDptEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class LoadDptDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    /**
     * Method Untuk Load DPT Saat Pertama Kali Buka Halaman Pendapatan
     */
    public List<LoadDptEntity> getLoadPendapatan(String tahunAnggaran, String skpdId) {
        String sql = "SELECT NVL(xxx.i_id, -1)          idTrx\n" +
                "     , tmrba.c_angg_tahun      AS tahunAnggaran\n" +
                "     , tmrba.i_idskpd          AS idSkpd\n" +
                "     , trrbaskpd.c_skpd        AS skpd\n" +
                "     , trrbaskpd.n_skpd        AS namaSkpd\n" +
                "     , trbas.i_id              AS idBas\n" +
                "     , c_akun                  AS kodeAkun\n" +
                "     , n_akun                  AS namaAkun\n" +
                "     , NVL(xxx.v_angg_dpa, 0)  AS anggaranDpa\n" +
                "     , NVL(xxx.v_angg_tapd, 0) AS anggaranTapd\n" +
                "FROM tmrba\n" +
                "         INNER JOIN trrbaskpd ON tmrba.i_idskpd = trrbaskpd.i_id\n" +
                "         INNER JOIN trrbaskpdbas ON trrbaskpd.i_id = trrbaskpdbas.i_idskpd\n" +
                "         INNER JOIN trbas ON trrbaskpdbas.i_idbas = trbas.i_id\n" +
                "         LEFT JOIN tmrbadpt xxx\n" +
                "                   ON tmrba.c_angg_tahun = xxx.c_angg_tahun\n" +
                "                       AND tmrba.i_idskpd = xxx.i_idskpd\n" +
                "                       AND trrbaskpdbas.i_idbas = xxx.i_idbas\n" +
                "WHERE trbas.c_akun BETWEEN '4' AND '4.9'\n" +
                "  AND tmrba.c_angg_tahun = :vtahun\n" +
                "  AND tmrba.i_idskpd = :vidskpd\n" +
                "ORDER BY tmrba.c_angg_tahun, trrbaskpd.c_skpd, c_akun ";
        Map<String, Object> param = new HashMap<>();
        param.put("vtahun", tahunAnggaran);
        param.put("vidskpd", skpdId);

        return this.namedParameterJdbcTemplate.query(sql, param, (resultSet, i) -> {
            LoadDptEntity loadDptEntity = new LoadDptEntity();
            loadDptEntity.setIdTrx(resultSet.getInt("idTrx"));
            loadDptEntity.setTahunAnggaran(resultSet.getString("tahunAnggaran"));
            loadDptEntity.setIdSkpd(resultSet.getInt("idSkpd"));
            loadDptEntity.setSkpd(resultSet.getString("skpd"));
            loadDptEntity.setNamaSkpd(resultSet.getString("namaSkpd"));
            loadDptEntity.setIdBas(resultSet.getInt("idBas"));
            loadDptEntity.setKodeAkun(resultSet.getString("kodeAkun"));
            loadDptEntity.setNamaAkun(resultSet.getString("namaAkun"));
            loadDptEntity.setAnggaranDpa(resultSet.getBigDecimal("anggaranDpa"));
            loadDptEntity.setAnggaranTapd(resultSet.getBigDecimal("anggaranTapd"));
            return loadDptEntity;
        });
    }

}
