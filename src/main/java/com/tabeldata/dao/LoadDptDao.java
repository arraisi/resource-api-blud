package com.tabeldata.dao;

import com.tabeldata.entity.LoadDpt;
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


    public List<LoadDpt> getLoadPendapatan(String tahunAnggaran, String skpdId) {
        String sql = "SELECT NVL(xxx.i_id, -1)          id_trx\n" +
                "     , tmrba.c_angg_tahun\n" +
                "     , tmrba.i_idskpd\n" +
                "     , trrbaskpd.c_skpd\n" +
                "     , trrbaskpd.n_skpd\n" +
                "     , trbas.i_id              AS i_idbas\n" +
                "     , c_akun\n" +
                "     , n_akun\n" +
                "     , NVL(xxx.v_angg_dpa, 0)  AS v_angg_dpa\n" +
                "     , NVL(xxx.v_angg_tapd, 0) AS v_angg_tapd\n" +
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
            LoadDpt loadDpt = new LoadDpt();
            loadDpt.setIdTrx(resultSet.getInt("id_trx"));
            loadDpt.setTahunAnggaran(resultSet.getString("c_angg_tahun"));
            loadDpt.setIdSkpd(resultSet.getInt("i_idskpd"));
            loadDpt.setSkpd(resultSet.getString("c_skpd"));
            loadDpt.setNamaSkpd(resultSet.getString("n_skpd"));
            loadDpt.setIdBas(resultSet.getInt("i_idbas"));
            loadDpt.setKodeAkun(resultSet.getString("c_akun"));
            loadDpt.setNamaAkun(resultSet.getString("n_akun"));
            loadDpt.setAnggaranDpa(resultSet.getDouble("v_angg_dpa"));
            loadDpt.setAnggaranTapd(resultSet.getDouble("v_angg_tapd"));
            return loadDpt;
        });
    }

}
