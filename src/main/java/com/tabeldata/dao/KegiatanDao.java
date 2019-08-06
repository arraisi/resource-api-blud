package com.tabeldata.dao;

import com.tabeldata.dto.LoadKegiatanDatatableDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class KegiatanDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<LoadKegiatanDatatableDto> loadKegiatanDatatable(String tahunAnggaran, Integer idSkpd) {

        String sql = "SELECT NVL(xxx.i_id, -1)        AS idKegiatan\n" +
                "     , tmrba.c_angg_tahun       AS tahunAnggaran\n" +
                "     , tmrba.i_idskpd           AS idSkpd\n" +
                "     , trrbaskpd.c_skpd         AS kodeSkpd\n" +
                "     , trrbaskpd.n_skpd         AS namaSkpd\n" +
                "     , trprogram.i_idurusan     AS idUrusan\n" +
                "     , c_urusan                 AS kodeUrusan\n" +
                "     , n_urusan                 AS namaUrusan\n" +
                "     , xxx.i_idprogram          AS idProgram\n" +
                "     , c_program                AS kodeProgram\n" +
                "     , n_program                AS namaProgram\n" +
                "     , NVL(xxx.c_kegiatan, '-') AS kodeKegiatan\n" +
                "     , NVL(xxx.n_kegiatan, '-') AS namaKegiatan\n" +
                "     , NVL(xxx.v_angg_dpa, 0)   AS anggaranDpa\n" +
                "     , NVL(xxx.v_angg_tapd, 0)  AS anggaranTapd\n" +
                "FROM tmrba\n" +
                "         INNER JOIN trrbaskpd ON (tmrba.i_idskpd = trrbaskpd.i_id)\n" +
                "         LEFT JOIN tmrbakegiatan xxx ON (tmrba.c_angg_tahun = xxx.c_angg_tahun) AND (tmrba.i_idskpd = xxx.i_idskpd)\n" +
                "         LEFT JOIN trprogram ON trprogram.i_id = xxx.i_idprogram\n" +
                "         LEFT JOIN trurusan ON trurusan.i_id = trprogram.i_idurusan\n" +
                "WHERE tmrba.c_angg_tahun = :vTahunAnggaran\n" +
                "  and tmrba.I_idskpd = :vIdSkpd\n" +
                "ORDER BY tmrba.i_idskpd, xxx.c_kegiatan ";
        Map<String, Object> params = new HashedMap<>();
        params.put("vTahunAnggaran", tahunAnggaran);
        params.put("vIdSkpd", idSkpd);

        return this.namedParameterJdbcTemplate.query(sql, params, (rs, i) -> {
            LoadKegiatanDatatableDto data = new LoadKegiatanDatatableDto();
            data.setIdKegiatan(rs.getInt("idKegiatan"));
            data.setTahunAnggaran(rs.getString("tahunAnggaran"));
            data.setIdSkpd(rs.getInt("idSkpd"));
            data.setKodeSkpd(rs.getString("kodeSkpd"));
            data.setNamaSkpd(rs.getString("namaSkpd"));
            data.setIdUrusan(rs.getInt("idUrusan"));
            data.setKodeUrusan(rs.getString("kodeUrusan"));
            data.setNamaUrusan(rs.getString("namaUrusan"));
            data.setIdProgram(rs.getInt("idProgram"));
            data.setKodeProgram(rs.getString("kodeProgram"));
            data.setNamaProgram(rs.getString("namaProgram"));
            data.setKodeKegiatan(rs.getString("kodeKegiatan"));
            data.setNamaKegiatan(rs.getString("namaKegiatan"));
            data.setAnggaranDpa(rs.getBigDecimal("anggaranDpa"));
            data.setAnggaranTapd(rs.getBigDecimal("anggaranTapd"));
            return data;
        });
    }

    public Integer getNoKegiatan(String tahunAnggaran, Integer idProgram, Integer idSkpd) throws EmptyResultDataAccessException {
        //language=Oracle
        String sql = "SELECT" +
                " CONCAT\n" +
                "           (\n" +
                "               CONCAT\n" +
                "                   (\n" +
                "                       TRP.C_PROGRAM, '.'\n" +
                "                   )\n" +
                "           , LPAD(COUNT(0) + 1, 3, '0')\n" +
                "           ) AS NO_KEGIATAN\n" +
                "FROM" +
                " TMRBAKEGIATAN \"TKG\"\n" +
                "         INNER JOIN TRPROGRAM \"TRP\" ON TRP.I_ID = TKG.I_IDPROGRAM\n" +
                "WHERE" +
                " TKG.I_IDSKPD = :vIdSkpd\n" +
                "  AND TKG.I_IDPROGRAM = :vIdProgram\n" +
                "  AND TKG.C_ANGG_TAHUN = :vTahunAnggaran\n" +
                "GROUP BY" +
                " TRP.C_PROGRAM ";

        Map<String, Object> params = new HashedMap<>();
        params.put("vTahunAnggaran", tahunAnggaran);
        params.put("vIdProgram", idProgram);
        params.put("vIdSkpd", idSkpd);

        return this.namedParameterJdbcTemplate.queryForObject(sql, params, (rs, i) -> {
            Integer noKegiatan = rs.getInt("NO_KEGIATAN");
            return noKegiatan;
        });
    }
}
