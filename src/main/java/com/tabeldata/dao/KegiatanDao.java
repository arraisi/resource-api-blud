package com.tabeldata.dao;

import com.tabeldata.dto.LoadKegiatanDatatableDto;
import com.tabeldata.entity.KegiatanEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class KegiatanDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Load Kegiatan List Table Awal
     */
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

    /**
     * Get No. Kegiatan
     */
    public String getNoKegiatan(String tahunAnggaran, Integer idProgram, Integer idSkpd) throws EmptyResultDataAccessException {
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

        return this.namedParameterJdbcTemplate.queryForObject(sql, params, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("NO_KEGIATAN");
            }
        });

    }


    /**
     * Save Kegiatan
     */
    public int saveKegiatan(KegiatanEntity value) {
        String sql = "INSERT\n" +
                "INTO TMRBAKEGIATAN (I_ID,\n" +
                "                    I_IDSKPD,\n" +
                "                    I_IDPROGRAM,\n" +
                "                    C_ANGG_TAHUN,\n" +
                "                    C_KEGIATAN,\n" +
                "                    N_KEGIATAN,\n" +
                "                    V_ANGG_TAPD,\n" +
                "                    V_ANGG_DPA,\n" +
                "                    C_WIL_LOKASI,\n" +
                "                    I_PGUN_REKAM,\n" +
                "                    D_PGUN_REKAM,\n" +
                "                    I_PGUN_UBAH,\n" +
                "                    D_PGUN_UBAH,\n" +
                "                    N_ANGG_SUMBDANA,\n" +
                "                    E_SASARAN)\n" +
                "VALUES (:id,\n" +
                "        :idSkpd,\n" +
                "        :idProgram,\n" +
                "        :tahunAnggaran,\n" +
                "        :kodeKegiatan,\n" +
                "        :namaKegiatan,\n" +
                "        :anggaranTapd,\n" +
                "        :anggaranDpa,\n" +
                "        :kodeLokasiKegiatan,\n" +
                "        :idPenggunaRekam,\n" +
                "        :tanggalPenggunaRekam,\n" +
                "        :idPenggunaUbah,\n" +
                "        :tanggalPenggunaUbah,\n" +
                "        :namaSumberDana,\n" +
                "        :sasaranKegiatan) ";
        Map<String, Object> params = new HashedMap<>();
        params.put("id", value.getId());
        params.put("idSkpd", value.getIdSkpd());
        params.put("idProgram", value.getIdProgram());
        params.put("tahunAnggaran", value.getTahunAnggaran());
        params.put("kodeKegiatan", value.getKodeKegiatan());
        params.put("namaKegiatan", value.getNamaKegiatan());
        params.put("anggaranTapd", value.getAnggaranTapd());
        params.put("anggaranDpa", value.getAnggaranDpa());
        params.put("kodeLokasiKegiatan", value.getKodeLokasiKegiatan());
        params.put("idPenggunaRekam", value.getIdPenggunaRekam());
        params.put("tanggalPenggunaRekam", value.getTanggalPenggunaRekam());
        params.put("idPenggunaUbah", value.getIdPenggunaUbah());
        params.put("tanggalPenggunaUbah", value.getTanggalPenggunaUbah());
        params.put("namaSumberDana", value.getNamaSumberDana());
        params.put("sasaranKegiatan", value.getSasaranKegiatan());
        return this.namedParameterJdbcTemplate.update(sql, params);
    }

    /**
     * Update Kegiatan (Catatan: Kalau Update Dari Service nya Tolong Get Dulu By ID biar Value Yang Lain Gak jadi Null)
     */
    public int updateKegiatan(KegiatanEntity value) {
        String sql = "UPDATE\n" +
                "    TMRBAKEGIATAN\n" +
                "SET I_IDSKPD        = :idSkpd,\n" +
                "    I_IDPROGRAM     = :idProgram,\n" +
                "    C_ANGG_TAHUN    = :tahunAnggaran,\n" +
                "    C_KEGIATAN      = :kodeKegiatan,\n" +
                "    N_KEGIATAN      = :namaKegiatan,\n" +
                "    V_ANGG_TAPD     = :anggaranTapd,\n" +
                "    V_ANGG_DPA      = :anggaranDpa,\n" +
                "    C_WIL_LOKASI    = :kodeLokasiKegiatan,\n" +
                "    I_PGUN_REKAM    = :idPenggunaRekam,\n" +
                "    D_PGUN_REKAM    = :tanggalPenggunaRekam,\n" +
                "    I_PGUN_UBAH     = :idPenggunaUbah,\n" +
                "    D_PGUN_UBAH     = :tanggalPenggunaUbah,\n" +
                "    N_ANGG_SUMBDANA = :namaSumberDana,\n" +
                "    E_SASARAN       = :sasaranKegiatan\n" +
                "WHERE I_ID = :id ";
        Map<String, Object> params = new HashedMap<>();
        params.put("id", value.getId());
        params.put("idSkpd", value.getIdSkpd());
        params.put("idProgram", value.getIdProgram());
        params.put("tahunAnggaran", value.getTahunAnggaran());
        params.put("kodeKegiatan", value.getKodeKegiatan());
        params.put("namaKegiatan", value.getNamaKegiatan());
        params.put("anggaranTapd", value.getAnggaranTapd());
        params.put("anggaranDpa", value.getAnggaranDpa());
        params.put("kodeLokasiKegiatan", value.getKodeLokasiKegiatan());
        params.put("idPenggunaRekam", value.getIdPenggunaRekam());
        params.put("tanggalPenggunaRekam", value.getTanggalPenggunaRekam());
        params.put("idPenggunaUbah", value.getIdPenggunaUbah());
        params.put("tanggalPenggunaUbah", value.getTanggalPenggunaUbah());
        params.put("namaSumberDana", value.getNamaSumberDana());
        params.put("sasaranKegiatan", value.getSasaranKegiatan());
        return this.namedParameterJdbcTemplate.update(sql, params);
    }

    /**
     * Get Kegiatan By ID
     */
    public KegiatanEntity getKegiatanByID(Integer id) {
        String sql = "SELECT I_ID            AS id,\n" +
                "       I_IDSKPD        AS idSkpd,\n" +
                "       I_IDPROGRAM     AS idProgram,\n" +
                "       C_ANGG_TAHUN    AS tahunAnggaran,\n" +
                "       C_KEGIATAN      AS kodeKegiatan,\n" +
                "       N_KEGIATAN      AS namaKegiatan,\n" +
                "       V_ANGG_TAPD     AS anggaranTapd,\n" +
                "       V_ANGG_DPA      AS anggaranDpa,\n" +
                "       C_WIL_LOKASI    AS kodeLokasiKegiatan,\n" +
                "       I_PGUN_REKAM    AS idPenggunaRekam,\n" +
                "       D_PGUN_REKAM    AS tanggalPenggunaRekam,\n" +
                "       I_PGUN_UBAH     AS idPenggunaUbah,\n" +
                "       D_PGUN_UBAH     AS tanggalPenggunaUbah,\n" +
                "       N_ANGG_SUMBDANA AS namaSumberDana,\n" +
                "       E_SASARAN       AS sasaranKegiatan\n" +
                "FROM TMRBAKEGIATAN\n" +
                "WHERE I_ID = :vId ";
        Map<String, Object> param = new HashedMap<>();
        param.put("vId", id);

        return this.namedParameterJdbcTemplate.queryForObject(sql, param, (rs, i) -> {
            KegiatanEntity value = new KegiatanEntity();
            value.setId(rs.getInt("id"));
            value.setIdSkpd(rs.getInt("idSkpd"));
            value.setIdProgram(rs.getInt("idProgram"));
            value.setTahunAnggaran(rs.getString("tahunAnggaran"));
            value.setKodeKegiatan(rs.getString("kodeKegiatan"));
            value.setNamaKegiatan(rs.getString("namaKegiatan"));
            value.setAnggaranTapd(rs.getBigDecimal("anggaranTapd"));
            value.setAnggaranDpa(rs.getBigDecimal("anggaranDpa"));
            value.setKodeLokasiKegiatan(rs.getString("kodeLokasiKegiatan"));
            value.setIdPenggunaRekam(rs.getInt("idPenggunaRekam"));
            value.setTanggalPenggunaRekam(rs.getTimestamp("tanggalPenggunaRekam"));
            value.setIdPenggunaUbah(rs.getInt("idPenggunaUbah"));
            value.setTanggalPenggunaUbah(rs.getTimestamp("tanggalPenggunaUbah"));
            value.setNamaSumberDana(rs.getString("namaSumberDana"));
            value.setSasaranKegiatan(rs.getString("sasaranKegiatan"));
            return value;
        });
    }
}