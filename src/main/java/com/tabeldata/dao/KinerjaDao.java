package com.tabeldata.dao;

import com.tabeldata.dto.LoadKinerjaDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class KinerjaDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<LoadKinerjaDto> loadKinerja(String tahunAnggaran, Integer idKegiatan, Integer idSkpd) {
        //language=Oracle
        String sql = "SELECT NVL(yyy.i_id, -1)        AS idKegiatanKinerja\n" +
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
                "     , xxx.I_ID                 AS idKegiatan\n" +
                "     , NVL(xxx.c_kegiatan, '-') AS kodeKegiatan\n" +
                "     , NVL(xxx.n_kegiatan, '-') AS namaKegiatan\n" +
                "     , NVL(xxx.v_angg_dpa, 0)   AS anggaranDpa\n" +
                "     , NVL(xxx.v_angg_tapd, 0)  AS anggaranTapd\n" +
                "     , yyy.c_pgm_indikator      AS kodeIndikator\n" +
                "     , trkoderef.e_org_fungsi   AS keteranganKodeFungsi\n" +
                "     , yyy.i_nourut             AS noUrut\n" +
                "     , yyy.e_pgm_tolokukur      AS keteranganTolakUkur\n" +
                "     , yyy.e_pgm_targkinerja    AS keteranganTargetKinerja\n" +
                "FROM tmrba\n" +
                "         INNER JOIN trrbaskpd ON (tmrba.i_idskpd = trrbaskpd.i_id)\n" +
                "         INNER JOIN tmrbakegiatan xxx ON (tmrba.c_angg_tahun = xxx.c_angg_tahun) AND (tmrba.i_idskpd = xxx.i_idskpd)\n" +
                "         LEFT JOIN tmrbakegiatankinerja yyy ON xxx.i_id = yyy.i_idkegiatan\n" +
                "         LEFT JOIN trkoderef ON trkoderef.c_org_fungsi = '02' AND trkoderef.c_org_fungsiref = yyy.c_pgm_indikator\n" +
                "         LEFT JOIN trprogram ON trprogram.i_id = xxx.i_idprogram\n" +
                "         LEFT JOIN trurusan ON trurusan.i_id = trprogram.i_idurusan\n" +
                "WHERE tmrba.c_angg_tahun = :vTahunAnggaran\n" +
                "  and tmrba.I_idskpd = :vIdSkpd\n" +
                "  and xxx.I_ID = :vIdKegiatan\n" +
                "ORDER BY tmrba.i_idskpd, xxx.c_kegiatan, yyy.c_pgm_indikator, yyy.i_nourut ";

        Map<String, Object> params = new HashedMap<>();
        params.put("vTahunAnggaran", tahunAnggaran);
        params.put("vIdSkpd", idSkpd);
        params.put("vIdKegiatan", idKegiatan);
//        return this.namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<LoadKinerjaDto>()); // Cara Cepat
        return this.namedParameterJdbcTemplate.query(sql, params, (rs, i) -> {
            LoadKinerjaDto data = new LoadKinerjaDto();
            data.setIdKegiatanKinerja(rs.getInt("idKegiatanKinerja"));
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
            data.setIdKegiatan(rs.getInt("idKegiatan"));
            data.setKodeKegiatan(rs.getString("kodeKegiatan"));
            data.setNamaKegiatan(rs.getString("namaKegiatan"));
            data.setAnggaranDpa(rs.getBigDecimal("anggaranDpa"));
            data.setAnggaranTapd(rs.getBigDecimal("anggaranTapd"));
            data.setKodeIndikator(rs.getString("kodeIndikator"));
            data.setKeteranganKodeFungsi(rs.getString("keteranganKodeFungsi"));
            data.setNoUrut(rs.getString("noUrut"));
            data.setKeteranganTolakUkur(rs.getString("keteranganTolakUkur"));
            data.setKeteranganTargetKinerja(rs.getString("keteranganTargetKinerja"));
            return data;
        });
    }
}
