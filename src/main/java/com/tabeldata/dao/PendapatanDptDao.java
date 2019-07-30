package com.tabeldata.dao;

import com.tabeldata.entity.PendapatanDptEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class PendapatanDptDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Method untuk Update Anggaran Pada Table TMRBADPT (Pendapatan)
     */
    public int updateAnggaranPendapatanById(PendapatanDptEntity value) throws EmptyResultDataAccessException {
        //language=Oracle
        String sql = "UPDATE\n" +
                "    TMRBADPT\n" +
                "SET V_ANGG_DPA  = :anggaranDpa,\n" +
                "    V_ANGG_TAPD = :anggaranTapd,\n" +
                "    I_PGUN_UBAH = :idUbahPengguna,\n" +
                "    D_PGUN_UBAH = :tanggalUbahPengguna\n" +
                "where I_ID = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", value.getId());
        params.put("anggaranDpa", value.getAnggaranDpa());
        params.put("anggaranTapd", value.getAnggaranTapd());
        params.put("idUbahPengguna", value.getIdUbahPengguna());
        params.put("tanggalUbahPengguna", value.getTanggalUbahPengguna());
        return this.namedParameterJdbcTemplate.update(sql, params);
    }

    /**
     * Method Update All Data Pendapatan
     */
    public int updateAllDataPendapatanById(PendapatanDptEntity value) throws EmptyResultDataAccessException {
        String sql = "UPDATE\n" +
                "    TMRBADPT\n" +
                "SET" +
                "    I_IDSKPD      = :idSkpd,\n" +
                "    I_IDBAS       = :idBas,\n" +
                "    C_ANGG_TAHUN  = :tahunAnggaran,\n" +
                "    V_ANGG_DPA    = :anggaranDpa,\n" +
                "    V_ANGG_TAPD   = :anggaranTapd,\n" +
                "    V_RPA_BULAN01 = :rpaBulan1,\n" +
                "    V_RPA_BULAN02 = :rpaBulan2,\n" +
                "    V_RPA_BULAN03 = :rpaBulan3,\n" +
                "    V_RPA_BULAN04 = :rpaBulan4,\n" +
                "    V_RPA_BULAN05 = :rpaBulan5,\n" +
                "    V_RPA_BULAN06 = :rpaBulan6,\n" +
                "    V_RPA_BULAN07 = :rpaBulan7,\n" +
                "    V_RPA_BULAN08 = :rpaBulan8,\n" +
                "    V_RPA_BULAN09 = :rpaBulan9,\n" +
                "    V_RPA_BULAN10 = :rpaBulan10,\n" +
                "    V_RPA_BULAN11 = :rpaBulan11,\n" +
                "    V_RPA_BULAN12 = :rpaBulan12,\n" +
                "    I_PGUN_REKAM  = :idRekamPengguna,\n" +
                "    D_PGUN_REKAM  = :tanggalRekamPengguna,\n" +
                "    I_PGUN_UBAH   = :idUbahPengguna,\n" +
                "    D_PGUN_UBAH   = :tanggalUbahPengguna,\n" +
                "    C_RPA_JENIS   = :jenis\n" +
                "where I_ID = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", value.getId());
        params.put("idSkpd", value.getIdSkpd());
        params.put("idBas", value.getIdBas());
        params.put("tahunAnggaran", value.getTahunAnggaran());
        params.put("anggaranDpa", value.getAnggaranDpa());
        params.put("anggaranTapd", value.getAnggaranTapd());
        params.put("rpaBulan1", value.getRpaBulan1());
        params.put("rpaBulan2", value.getRpaBulan2());
        params.put("rpaBulan3", value.getRpaBulan3());
        params.put("rpaBulan4", value.getRpaBulan4());
        params.put("rpaBulan5", value.getRpaBulan5());
        params.put("rpaBulan6", value.getRpaBulan6());
        params.put("rpaBulan7", value.getRpaBulan7());
        params.put("rpaBulan8", value.getRpaBulan8());
        params.put("rpaBulan9", value.getRpaBulan9());
        params.put("rpaBulan10", value.getRpaBulan10());
        params.put("rpaBulan11", value.getRpaBulan11());
        params.put("rpaBulan12", value.getRpaBulan12());
        params.put("idRekamPengguna", value.getIdRekamPengguna());
        params.put("tanggalRekamPengguna", value.getTanggalRekamPengguna());
        params.put("idUbahPengguna", value.getIdUbahPengguna());
        params.put("tanggalUbahPengguna", value.getTanggalUbahPengguna());
        params.put("jenis", value.getJenis());
        return this.namedParameterJdbcTemplate.update(sql, params);
    }

    /**
     * Method Save pendapatan
     */
    public int savePendapatan(PendapatanDptEntity value) throws EmptyResultDataAccessException {
        //language=Oracle
        String sql = "INSERT INTO TMRBADPT (I_ID,\n" +
                "                      I_IDSKPD,\n" +
                "                      I_IDBAS,\n" +
                "                      C_ANGG_TAHUN,\n" +
                "                      V_ANGG_DPA,\n" +
                "                      V_ANGG_TAPD,\n" +
                "                      V_RPA_BULAN01,\n" +
                "                      V_RPA_BULAN02,\n" +
                "                      V_RPA_BULAN03,\n" +
                "                      V_RPA_BULAN04,\n" +
                "                      V_RPA_BULAN05,\n" +
                "                      V_RPA_BULAN06,\n" +
                "                      V_RPA_BULAN07,\n" +
                "                      V_RPA_BULAN08,\n" +
                "                      V_RPA_BULAN09,\n" +
                "                      V_RPA_BULAN10,\n" +
                "                      V_RPA_BULAN11,\n" +
                "                      V_RPA_BULAN12,\n" +
                "                      I_PGUN_REKAM,\n" +
                "                      D_PGUN_REKAM,\n" +
                "                      I_PGUN_UBAH,\n" +
                "                      D_PGUN_UBAH,\n" +
                "                      C_RPA_JENIS)\n" +
                "VALUES (:id,\n" +
                "        :idSkpd,\n" +
                "        :idBas,\n" +
                "        :tahunAnggaran,\n" +
                "        :anggaranDpa,\n" +
                "        :anggaranTapd,\n" +
                "        :rpaBulan1,\n" +
                "        :rpaBulan2,\n" +
                "        :rpaBulan3,\n" +
                "        :rpaBulan4,\n" +
                "        :rpaBulan5,\n" +
                "        :rpaBulan6,\n" +
                "        :rpaBulan7,\n" +
                "        :rpaBulan8,\n" +
                "        :rpaBulan9,\n" +
                "        :rpaBulan10,\n" +
                "        :rpaBulan11,\n" +
                "        :rpaBulan12,\n" +
                "        :idRekamPengguna,\n" +
                "        :tanggalRekamPengguna,\n" +
                "        :idUbahPengguna,\n" +
                "        :tanggalUbahPengguna,\n" +
                "        :jenis)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", value.getId());
        params.put("idSkpd", value.getIdSkpd());
        params.put("idBas", value.getIdBas());
        params.put("tahunAnggaran", value.getTahunAnggaran());
        params.put("anggaranDpa", value.getAnggaranDpa());
        params.put("anggaranTapd", value.getAnggaranTapd());
        params.put("rpaBulan1", value.getRpaBulan1());
        params.put("rpaBulan2", value.getRpaBulan2());
        params.put("rpaBulan3", value.getRpaBulan3());
        params.put("rpaBulan4", value.getRpaBulan4());
        params.put("rpaBulan5", value.getRpaBulan5());
        params.put("rpaBulan6", value.getRpaBulan6());
        params.put("rpaBulan7", value.getRpaBulan7());
        params.put("rpaBulan8", value.getRpaBulan8());
        params.put("rpaBulan9", value.getRpaBulan9());
        params.put("rpaBulan10", value.getRpaBulan10());
        params.put("rpaBulan11", value.getRpaBulan11());
        params.put("rpaBulan12", value.getRpaBulan12());
        params.put("idRekamPengguna", value.getIdRekamPengguna());
        params.put("tanggalRekamPengguna", value.getTanggalRekamPengguna());
        params.put("idUbahPengguna", value.getIdUbahPengguna());
        params.put("tanggalUbahPengguna", value.getTanggalUbahPengguna());
        params.put("jenis", value.getJenis());
        return this.namedParameterJdbcTemplate.update(sql, params);
    }

    /**
     * Method get pendapatan By ID Pendapatan
     */
    public PendapatanDptEntity getPendapatanDpt(Integer id) {
        String sql = "SELECT I_ID          AS id,\n" +
                "       I_IDSKPD      AS idSkpd,\n" +
                "       I_IDBAS       AS idBas,\n" +
                "       C_ANGG_TAHUN  AS tahunAnggaran,\n" +
                "       V_ANGG_DPA    AS anggaranDpa,\n" +
                "       V_ANGG_TAPD   AS anggaranTapd,\n" +
                "       V_RPA_BULAN01 AS rpaBula1,\n" +
                "       V_RPA_BULAN02 AS rpaBula2,\n" +
                "       V_RPA_BULAN03 AS rpaBula3,\n" +
                "       V_RPA_BULAN04 AS rpaBula4,\n" +
                "       V_RPA_BULAN05 AS rpaBula5,\n" +
                "       V_RPA_BULAN06 AS rpaBula6,\n" +
                "       V_RPA_BULAN07 AS rpaBula7,\n" +
                "       V_RPA_BULAN08 AS rpaBula8,\n" +
                "       V_RPA_BULAN09 AS rpaBula9,\n" +
                "       V_RPA_BULAN10 AS rpaBula10,\n" +
                "       V_RPA_BULAN11 AS rpaBula11,\n" +
                "       V_RPA_BULAN12 AS rpaBula12,\n" +
                "       I_PGUN_REKAM  AS idRekamPengguna,\n" +
                "       D_PGUN_REKAM  AS tanggalRekamPengguna,\n" +
                "       I_PGUN_UBAH   AS idUbahPengguna,\n" +
                "       D_PGUN_UBAH   AS tanggalUbahPengguna,\n" +
                "       C_RPA_JENIS   AS jenis\n" +
                "FROM TMRBADPT\n" +
                "WHERE I_ID = :id";
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        return this.namedParameterJdbcTemplate.queryForObject(sql, param, new RowMapper<PendapatanDptEntity>() {
            @Override
            public PendapatanDptEntity mapRow(ResultSet rs, int i) throws SQLException {
                return new PendapatanDptEntity(
                        rs.getInt("id"),
                        rs.getInt("idSkpd"),
                        rs.getInt("idBas"),
                        rs.getString("tahunAnggaran"),
                        rs.getBigDecimal("anggaranDpa"),
                        rs.getBigDecimal("anggaranTapd"),
                        rs.getBigDecimal("rpaBula1"),
                        rs.getBigDecimal("rpaBula2"),
                        rs.getBigDecimal("rpaBula3"),
                        rs.getBigDecimal("rpaBula4"),
                        rs.getBigDecimal("rpaBula5"),
                        rs.getBigDecimal("rpaBula6"),
                        rs.getBigDecimal("rpaBula7"),
                        rs.getBigDecimal("rpaBula8"),
                        rs.getBigDecimal("rpaBula9"),
                        rs.getBigDecimal("rpaBula10"),
                        rs.getBigDecimal("rpaBula11"),
                        rs.getBigDecimal("rpaBula12"),
                        rs.getInt("idRekamPengguna"),
                        rs.getTimestamp("tanggalRekamPengguna"),
                        rs.getInt("idUbahPengguna"),
                        rs.getTimestamp("tanggalUbahPengguna"),
                        rs.getString("jenis")
                );
            }
        });
    }
}
